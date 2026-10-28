BaseTest.java
What it does (good interview material):
1. Capabilities (UiAutomator2Options) tell Appium what to automate: platformName is Android, automationName is the UiAutomator2 driver you installed, and app is the APK path. Appium installs and launches it.
2. setNewCommandTimeout(120s) ends the session if no command arrives for 2 minutes, which prevents stuck sessions.
3. The URL http://127.0.0.1:4723 is your Appium server. There’s no /wd/hub in Appium 2 and 3.
4. @BeforeMethod runs before every test and creates a fresh session, so tests stay independent. @AfterMethod(alwaysRun = true) always quits the session, even when the test fails, so sessions don’t leak.

ApiDemosPage.java
What it does (interview material):
1. Page Object Model: the locators and screen actions live here, and tests only call methods like tap("Views"). If the UI changes, you fix one class instead of every test.
2. WebDriverWait (explicit wait): mobile screens animate and load. Instead of fixed sleeps, the wait polls for up to 10 seconds until the element is ready. That’s both faster and more stable.
3. AppiumBy.accessibilityId: the preferred locator. It’s fast and stable, and on Android it maps to the element’s content-desc.
4. AppiumBy.id: finds by resource ID. We use it for the Android system dialog.
5. UiScrollable: an Android-specific selector that scrolls a list until it finds the text you give it.

ApiDemosTest.java
What it does (interview material):
1. extends BaseTest means each test gets a fresh Appium session, because @BeforeMethod and @AfterMethod run around every @Test.
2. Tests read like user steps (tap Views, check Buttons is visible), and all locator details stay in the page class.
3. Assert.assertTrue(condition, message) is TestNG’s assertion. If the condition is false, the test fails and the message appears in the report.

testng.xml
What it does: it’s the TestNG suite file. It lists which test classes to run, and Surefire (set up in your pom.xml) reads it when you run mvn test

android-tests.yml
What each part does (interview material):
1. on: runs the workflow on pushes to main, on pull requests, and manually from the Actions tab (workflow_dispatch).
2. runs-on: ubuntu-latest gives you a fresh Linux machine for every run, so nothing from your laptop carries over. That’s why we install everything in the steps.
3. setup-java and setup-node install JDK 17 and Node 22, matching your laptop. cache: maven caches the dependencies so later runs are faster.
4. Install Appium puts the same Appium and UiAutomator2 driver on the runner that you installed by hand.
5. Enable KVM turns on hardware acceleration. Without it, the emulator is too slow to be usable.
6. android-emulator-runner boots an Android emulator, then runs the lines under script:. Each line is a separate command: start Appium in the background (&), wait until it answers on /status, then run mvn.
7. Upload reports runs with if: always(), so you get the reports and Appium log even when tests fail, which is exactly when you need them.