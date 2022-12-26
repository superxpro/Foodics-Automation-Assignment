package amazonPages;

import com.shaft.gui.element.ElementActions;
import com.shaft.tools.io.JSONFileManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PasswordPage {

    private static WebDriver driver;
    private static JSONFileManager testData = new JSONFileManager("src/test/resources/TestDataFiles/amazonLoginCredentials.json");

    private static final By password_label = By.xpath("//label[contains(text(),'Password')]");
    private static final By passwordField = By.id("ap_password");
    private static final By signIn_button = By.id("signInSubmit");
    private static final By hello_message = By.id("nav-link-accountList-nav-line-1");


    public PasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public static By getPasswordLabel() {
        return password_label;
    }

    public static By getHello_message(){
        return hello_message;
    }

    /**
     * Sends the password to the password field on the password page and clicks the sign in button.
     */
    public static void sendPassword() {
        (new ElementActions(driver))
                .type(passwordField, testData.getTestData("password"))
                .click(signIn_button);
    }

}
