package amazonPages;

import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private static WebDriver driver;
    private static final By proceedToBuy = By.xpath("//input[@name='proceedToRetailCheckout']");


    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Click on the Proceed to Buy button and go to the Checkout page.
     *
     * @return a CheckOutPage object representing the Checkout page
     */
    public static CheckOutPage clickOnTheProceedToBuyButton(){
        // Click on the Proceed to Buy button
        ElementActions.click(driver, proceedToBuy);
        // Return a CheckOutPage object representing the Checkout page
        return new CheckOutPage(driver);
    }



}
