package amazonTestCases;

import amazonPages.*;
import com.jcraft.jsch.DHEC256;
import com.shaft.driver.DriverFactory;
import com.shaft.gui.element.ElementActions;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Validations;
import io.cucumber.java.hu.De;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTests {
    private WebDriver driver;


    @Description("Check that the Side Menu is Displayed")
    @Test
    public void checkSideMenu() {
       // AllVideGamesPage.emptyTheCartBeforeTest();
        AmazonHomePage.openTheSideMenu();
        Validations.verifyThat()
                .element(driver, AmazonHomePage.getAllSideMenu_icon())
                .exists()
                .withCustomReportMessage("Side Menu is Displayed")
                .perform();
    }

    @Description("Given That the user Click on the login button, When User Enters Valid Email, Then the Password Field Should be displayed")
    @Test()
    public void checkEmailIsEntered() {
        AmazonHomePage.openSignInMenu();
        EmailPage.sendEmail();
        PasswordPage.getPasswordLabel();

        Validations.verifyThat()
                .element(driver, PasswordPage.getPasswordLabel())
                .text()
                .contains("Password")
                .withCustomReportMessage("User Enter His Email")
                .perform();
    }

    @Description("Given That the user enters his email, When User users his password, Then The User should be logged In")
    @Test(dependsOnMethods = "checkEmailIsEntered")
    public void checkPasswordIsEntered() {
        PasswordPage.sendPassword();
        PasswordPage.getHello_message();

        Validations.verifyThat()
                .element(driver, PasswordPage.getHello_message())
                .text()
                .contains("Alaa")
                .withCustomReportMessage("User Is Logged In Successfully")
                .perform();
    }

    @Description("Given That the side menu is opened, When i clicked on Expand Arrow, Then the menu will expand")
    @Test(dependsOnMethods = "checkSideMenu")
    public void checkExpandTheMenu() {
        AmazonHomePage.clickOnExpandMenu();
        Validations.verifyThat()
                .element(driver, AmazonHomePage.getExpandSideMenu_icon())
                .isVisible()
                .withCustomReportMessage("The menu is expanded")
                .perform();
    }

    @Description("Given That the Video Games is displayed, When i Clicked on it, Then Video Games Menu will be displayed")
    @Test(dependsOnMethods = "checkExpandTheMenu")
    public void checkThatTheVideoGamesMenuIsDisplayed() {
        AmazonHomePage.clickOnVideoGames();
        AmazonHomePage.getVideoGames_link();

        Validations.verifyThat()
                .element(driver, AmazonHomePage.getVideoGames_link())
                .isVisible()
                .withCustomReportMessage("Video Games Menu is Displayed")
                .perform();
    }

    @Description("Given That the Video Games menu is displayed, When i Clicked on All Video Games, Then All Video Games Page will be displayed")
    @Test(dependsOnMethods = "checkThatTheVideoGamesMenuIsDisplayed")
    public void checkThatTheAllVideoGamesISDisplayed() {
        AmazonHomePage.clickOnAllVideoGames();


        Validations.verifyThat()
                .element(driver, AmazonHomePage.getVideoGamesPageTitle())
                .text()
                .contains("Video Games")
                .withCustomReportMessage("The Video Games Page is Opened")
                .perform();
    }

    @Description("Given That the Video Games Page is opened, When i clicked on Free Shipping, Then The Results Should be filtered based on the Free Option")
    @Test(dependsOnMethods = "checkThatTheAllVideoGamesISDisplayed")
    public void checkThatTheFreeShippingISChecked() {
        AllVideGamesPage.clickOnTheFreeShipping();
        Validations.verifyThat()
                .element(driver, AllVideGamesPage.getFreeShippingLabel())
                .text()
                .contains("Eligible for Free Shipping")
                .perform();
    }

    @Description("Given That the All Video Games Results Page is Displayed, When i click on New Filter, Then the Results Should be filtered By New Option")
    @Test(dependsOnMethods = "checkThatTheFreeShippingISChecked")
    public void checkThatTheNewFilterIsApplied() {
        AllVideGamesPage.clickOnTheNewFilter_Option();
        Validations.verifyThat()
                .element(driver, AllVideGamesPage.getItemsCondition())
                .text()
                .contains("Any Condition")
                .withCustomReportMessage("The Results Page Filtered By the New Condition Filter")
                .perform();
    }

    @Description("Given That The All Video Games Pages Is Displayed, When I Sort price:High To Low, The The Results Should Be Sorted Properly")
    @Test(dependsOnMethods = "checkThatTheNewFilterIsApplied")
    public void CheckThatTheResultsSorted() {
        AllVideGamesPage.clickOnTheSortList();
        AllVideGamesPage.clickOnTheHighToLowSort();
        Validations.verifyThat()
                .element(driver, AllVideGamesPage.getHighToLowOption_text())
                .text()
                .contains("Price: High to Low")
                .withCustomReportMessage("The High To Low Sort Is Applied")
                .perform();
    }

    @Description("Given That I want to add 2-3 it`s below 15K, When I found item it`s price less than 15k, Then the item will be added to the cart")
    @Test(dependsOnMethods = "CheckThatTheResultsSorted")
    public void testAddToCartLogic() {
        AllVideGamesPage.addProductToCart();
        //String currentWindow = BrowserActions.getCurrentWindowTitle(driver);
        AllVideGamesPage.openCartPage();
        Validations.verifyThat()
                .element(driver, By.id("sc-buy-box-ptc-button"))
                .text()
                .contains("Proceed to Buy")
                .perform();
    }

    @Description("Given That I added items to my cart, When I Clicked On The CArt Icon, Then My Cart Should Opened And Contained My Items")
    @Test(dependsOnMethods = "testAddToCartLogic")
    public void testCartItems() {
        AllVideGamesPage.openCartPage();
        AllVideGamesPage.checkItemsInTheCart();
        Validations.verifyThat()
                .object(AllVideGamesPage.checkItemsInTheCart())
                .isTrue()
                .withCustomReportMessage(" The Items price is less than 15000 and the items count is between 1 and 3")
                .perform();
        CartPage.clickOnTheProceedToBuyButton();
    }


    @Description("Given That I LoggedIn, When I Clicked On Change Address, Then I Can Add Anew Address")
    @Test(dependsOnMethods = "testCartItems")
    public void checkAddNewAddress() {
        //CheckOutPage.clickOnChangeAddressButton();
       // CheckOutPage.clickOnTheAddNewAddressButton();
        CheckOutPage.fillOutTheAddressForm();
        CheckOutPage.getAddressInfoRow();

        Validations.verifyThat()
                .element(driver, CheckOutPage.getAddressInfoRow())
                .text()
                .contains("Shipping address")
                .perform();
    }

    @Description("Given That User in the Checkout Page, When User Choose the COD Pay Option, Then The COD Option Should be selected")
    @Test(dependsOnMethods = "checkAddNewAddress")
    public void checkCODOptionIsSelected(){
        CheckOutPage.clickOnTheCODOption();
        CheckOutPage.getCODOption();
        Validations.verifyThat()
                .element(driver, CheckOutPage.getUsePaymentMethodButton_status())
                .isEnabled()
                .perform();
    }


    @BeforeTest
    public void setUp() {
        driver = DriverFactory.getDriver();
        new AmazonHomePage(driver).navigate();
        new EmailPage(driver);
        new PasswordPage(driver);
        new AllVideGamesPage(driver);
        new CartPage(driver);
        new CheckOutPage(driver);
    }


}
