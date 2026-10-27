package com.hemant.qa.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApiDemosPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public ApiDemosPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void tap(String accessibilityId) {
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId(accessibilityId))).click();
    }

    public boolean isVisible(String accessibilityId) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(accessibilityId))).isDisplayed();
    }

    public boolean isDialogTitleVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("android:id/alertTitle"))).isDisplayed();
    }

    public void confirmDialog() {
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/button1"))).click();
    }

    public WebElement scrollToText(String text) {
        String selector = "new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";
        return driver.findElement(AppiumBy.androidUIAutomator(selector));
    }
}