package com.tutorialninja.testsuite;

import com.tutorialninja.customlisteners.CustomListeners;
import com.tutorialninja.pages.TopMenuPage;
import com.tutorialninja.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class TopMenuPageTest extends BaseTest {

    TopMenuPage topMenuPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        topMenuPage = new TopMenuPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        topMenuPage.mouseHoverToElementAndClickOnDesktop();
        topMenuPage.selectMenu("Show All Desktops");
        Assert.assertEquals(topMenuPage.getTextDesktop(), "Desktops", "Message not displayed");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        topMenuPage.mouseHoverOnLaptopsAndNotebooksTab();
        topMenuPage.selectMenu("Show All Laptops & Notebooks");
        Assert.assertEquals(topMenuPage.getLaptopsAndNotebooksText(), "Laptops & Notebooks", "Massage not displayed");
    }

    @Test(groups = {"regression"})
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        topMenuPage.mouseHoverToComponentsTab();
        topMenuPage.selectMenu("Show All Components");
        Assert.assertEquals(topMenuPage.getComponentsText(), "Components", "Message not displayed");

    }

}
