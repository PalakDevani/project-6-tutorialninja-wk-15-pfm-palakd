package com.tutorialninja.testsuite;

import com.tutorialninja.customlisteners.CustomListeners;
import com.tutorialninja.pages.DesktopPage;
import com.tutorialninja.pages.LaptopsAndNotebooksPage;
import com.tutorialninja.testbase.BaseTest;
import com.tutorialninja.testbase.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class LaptopsAndNotebooksPageTest extends BaseTest {
    LaptopsAndNotebooksPage laptopsAndNotebooksPage;
    DesktopPage desktopPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        laptopsAndNotebooksPage = new LaptopsAndNotebooksPage();
        desktopPage = new DesktopPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        laptopsAndNotebooksPage.mouseHoverOnLaptopsAndDesktopTab();
        laptopsAndNotebooksPage.clickOnShowAllLaptopsAndNotebooks();
        laptopsAndNotebooksPage.sortByPriceHighToLow();
        laptopsAndNotebooksPage.GetSortByHighToLowOrder();
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        laptopsAndNotebooksPage.clickOnCurrencyLink();
        laptopsAndNotebooksPage.mouseHoverOnLaptopsAndDesktopTab();
        laptopsAndNotebooksPage.clickOnShowAllLaptopsAndNotebooks();
        laptopsAndNotebooksPage.sortByPriceHighToLow();
        laptopsAndNotebooksPage.clickOnMacBookTab();
        laptopsAndNotebooksPage.clickOnAddToCartButton();
        String expectedText = "Success: You have added MacBook to your shopping cart!\n" + "×";
        Assert.assertEquals(laptopsAndNotebooksPage.getSuccessTextFromLink(), expectedText, "Error Message not displayed");
        laptopsAndNotebooksPage.clickOnShoppingCart();
        Thread.sleep(3000);
        Assert.assertEquals(laptopsAndNotebooksPage.getShoppingCartMessage(), "Shopping Cart", "Message not displayed");
        Assert.assertEquals(laptopsAndNotebooksPage.getVerifyProductMacbook(), "MacBook", "Message not displayed");

        Actions actions1 = new Actions(driver);
        WebElement quantity = driver.findElement(By.xpath("//input[contains(@name, 'quantity')]"));
        quantity.clear();
        sendTextToElement(By.xpath("//input[contains(@name, 'quantity')]"), "2");

        laptopsAndNotebooksPage.clickOnUpdateCart();
        String expectedMessage = "Success: You have modified your shopping cart!\n" + "×";
        Assert.assertEquals(laptopsAndNotebooksPage.getSuccessModifiedCartMessage(), expectedMessage, "Message not displayed");
        Assert.assertEquals(laptopsAndNotebooksPage.getVerifyTotal(), "£737.45", "Price Not displayed");
        laptopsAndNotebooksPage.clickOnCheckoutButton();
        Assert.assertEquals(laptopsAndNotebooksPage.getCheckoutText(), "Checkout", "Message not displayed");
        Thread.sleep(2000);
        String expectedNewCustomer = "New Customer";
        Assert.assertEquals(laptopsAndNotebooksPage.getNewCustomerText(), expectedNewCustomer, "Message not displayed");
        laptopsAndNotebooksPage.clickOnGuestCheckoutRadioButton();
        laptopsAndNotebooksPage.clickOnContinueTab();
        laptopsAndNotebooksPage.enterName("Devani");
        laptopsAndNotebooksPage.enterLastName("Carli");
        laptopsAndNotebooksPage.enterEmail("devc123" + getAlphaNumericString(3) + "@gmail.com");
        laptopsAndNotebooksPage.enterTelephone("1234567890");
        laptopsAndNotebooksPage.enterAddress("1,kenton road");
        laptopsAndNotebooksPage.enterCity("Harrow");
        laptopsAndNotebooksPage.enterPostcode("HA8 5NN");
        laptopsAndNotebooksPage.enterCountry("America");
        laptopsAndNotebooksPage.enterZone("Angus");
        laptopsAndNotebooksPage.clickOnContinueButton();
        laptopsAndNotebooksPage.enterTextToTextArea("Please confirm delivery date");
        laptopsAndNotebooksPage.clickOnTermsAndConditionsCheckbox();
        laptopsAndNotebooksPage.clickOnFinalContinueButton();
        String expecetdPaymentText = "Warning: Payment method required!\n" + "×";
        Assert.assertEquals(laptopsAndNotebooksPage.getWarningPaymentMethodRequiredText(), expecetdPaymentText, "Message not displayed");
    }
}
