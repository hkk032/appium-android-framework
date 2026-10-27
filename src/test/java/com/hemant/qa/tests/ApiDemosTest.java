package com.hemant.qa.tests;

import com.hemant.qa.base.BaseTest;
import com.hemant.qa.pages.ApiDemosPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiDemosTest extends BaseTest {

    @Test(description = "Views menu lists the Buttons option")
    public void viewsMenuShowsButtonsOption() {
        ApiDemosPage page = new ApiDemosPage(driver);
        page.tap("Views");
        Assert.assertTrue(page.isVisible("Buttons"), "Buttons should be visible under Views");
    }

    @Test(description = "Alert dialog opens and can be dismissed")
    public void alertDialogOpensAndCloses() {
        ApiDemosPage page = new ApiDemosPage(driver);
        page.tap("App");
        page.tap("Alert Dialogs");
        page.tap("OK Cancel dialog with a message");
        Assert.assertTrue(page.isDialogTitleVisible(), "Alert dialog title should be displayed");
        page.confirmDialog();
    }

    @Test(description = "Scroll the Views list to reach a lower item")
    public void canScrollToTabsOption() {
        ApiDemosPage page = new ApiDemosPage(driver);
        page.tap("Views");
        Assert.assertTrue(page.scrollToText("Tabs").isDisplayed(), "Tabs should be reachable by scrolling");
    }
}