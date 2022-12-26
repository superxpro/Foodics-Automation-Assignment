package amazonPages;

import com.shaft.gui.element.ElementActions;
import com.shaft.tools.io.JSONFileManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailPage {
    private static WebDriver driver;
    private static JSONFileManager testData = new JSONFileManager("src/test/resources/TestDataFiles/amazonLoginCredentials.json");

    private static final By emailField = By.id("ap_email");
    private static final By continue_button = By.xpath("//input[@id='continue']");


    public EmailPage(WebDriver driver){
        this.driver = driver;
    }

    public static PasswordPage sendEmail(){
        // Type the test email in the email field
        (new ElementActions(driver))
                .type(emailField, testData.getTestData("email"))
                // Click on the "Continue" button to submit the form
                .click(driver, continue_button);

        return new PasswordPage(driver);
    }

}
