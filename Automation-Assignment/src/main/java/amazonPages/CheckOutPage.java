package amazonPages;

import com.shaft.gui.element.ElementActions;
import com.shaft.tools.io.JSONFileManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutPage {

    private static WebDriver driver;
    private static JSONFileManager locationData = new JSONFileManager("src/test/resources/TestDataFiles/LocationInfo.json");

    private static final By changeAddress_button = By.id("addressChangeLinkId");
    private static final By addNewAddress = By.id("add-new-address-popover-link");

    private static final By mobileNum_field = By.id("address-ui-widgets-enterAddressPhoneNumber");
    private static final By streetName_field = By.id("address-ui-widgets-enterAddressLine1");
    private static final By buildingName_field = By.id("address-ui-widgets-enter-building-name-or-number");
    private static final By floorNum_field = By.id("address-ui-widgets-enter-floor-or-apartment-number");
    private static final By cityName_field = By.id("address-ui-widgets-enterAddressCity");
    private static final By district_field = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    private static final By home_radioButton = By.xpath("//input[@id=\"address-ui-widgets-addr-details-res-radio-input\"]");
    private static final By addAddress_button = By.xpath("//parent::span//input[@aria-labelledby='address-ui-widgets-form-submit-button-announce']");
    private static final By cachOnDelivery_Button = By.xpath("//span[@class='a-color-base']");
    private static final By addressInfoRow = By.xpath("//span[contains(.,'Shipping address')]");

    private static final By usePaymentMethod_button = By.xpath("//span[@id='orderSummaryPrimaryActionBtn-announce']");


    private static final By fullName_field = By.id("address-ui-widgets-enterAddressFullName");

    public static By getFullName_field() {
        return fullName_field;
    }

    public static By getMobileNum_field() {
        return mobileNum_field;
    }

    public static By getStreetName_field() {
        return streetName_field;
    }

    public static By getBuildingName_field() {
        return buildingName_field;
    }

    public static By getFloorNum_field() {
        return floorNum_field;
    }

    public static By getCityName_field() {
        return cityName_field;
    }

    public static By getDistrict_field() {
        return district_field;
    }

    public static By getHome_radioButton() {
        return home_radioButton;
    }

    public static By getAddAddress_button() {
        return addAddress_button;
    }


    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Click on the Change Address button.
     */
    public static void clickOnChangeAddressButton() {
        // Click on the Change Address button
        ElementActions.click(driver, changeAddress_button);

    }

    /**
     * Click on the Add New Address button.
     */
    public static void clickOnTheAddNewAddressButton() {
        // Click on the Add New Address button
        ElementActions.click(driver, addNewAddress);

    }

    public static void fillOutTheAddressForm() {
        (new ElementActions(driver))
                .type(CheckOutPage.getFullName_field(), locationData.getTestData("FullName"))
                .type(CheckOutPage.getMobileNum_field(), locationData.getTestData("MobileNumber"))
                .type(CheckOutPage.getStreetName_field(), locationData.getTestData("StreetName"))
                .type(CheckOutPage.getBuildingName_field(), locationData.getTestData("BuildingName"))
                .type(CheckOutPage.getFloorNum_field(), locationData.getTestData("Floor"))
                .type(CheckOutPage.getCityName_field(), locationData.getTestData("City"))
                .keyPress(CheckOutPage.getCityName_field(), Keys.ARROW_DOWN)
                .keyPress(CheckOutPage.getCityName_field(), Keys.ENTER)
                .type(CheckOutPage.getDistrict_field(), locationData.getTestData("District"))
                .keyPress(CheckOutPage.getDistrict_field(), Keys.ARROW_DOWN)
                .keyPress(CheckOutPage.getDistrict_field(), Keys.ENTER)
                .click(CheckOutPage.getHome_radioButton())
                .click(CheckOutPage.getAddAddress_button());
    }

    public static void clickOnTheCODOption() {
        WebElement codRadioButton = driver.findElement(cachOnDelivery_Button);
        codRadioButton.click();
    }

    public static By getAddressInfoRow() {
        return addressInfoRow;
    }

    public static By getCODOption() {
        return cachOnDelivery_Button;
    }

    public static By getUsePaymentMethodButton_status() {
        return usePaymentMethod_button;
    }

}
