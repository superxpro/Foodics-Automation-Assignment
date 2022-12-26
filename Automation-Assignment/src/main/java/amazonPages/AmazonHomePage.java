package amazonPages;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage {

    //variables
    private static WebDriver driver;

    private final String url = "https://www.amazon.eg/?language=en_AE";
    private static final By singInMenu = By.xpath("//a[@id='nav-link-accountList']");
    private static final By signIn_button = By.xpath("//a[@data-nav-ref='nav_signin']");
    private static final By changeLang_button = By.xpath("//a[@id='icp-nav-flyout']");
    private static final By englishLang_button = By.xpath("//div[@id='nav-flyout-icp']//a[contains(@class,'nav-item')]//i[contains(@class,'icp-radio')]");
    private static final By allVideoGames_link = By.xpath("//a[text()=\"All Video Games\"]");

    private static final By allSideMenu_icon = By.xpath("//span[@class='hm-icon-label']");
    private static final By expandSideMenu_icon = By.xpath("//div[text()=\"see all\"]");
    private static final By videoGamesPageTitle = By.xpath("//b[contains(text(),'Video Games')]");
    private static final By videoGames_link = By.xpath("//a[@data-menu-id='16']");
    private static final By videoGamesSection_title = By.xpath("//div[@class='fst-h1-st pageBanner']");
    private static final By nav_Bar = By.id("nav-main");


    //locators


    public static By getAmazonLogo_image() {
        return By.id("nav-logo-sprites");
    }

    //constructor
    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }

    //keywords
    /**
     * Navigate to the All Video Games page.
     */
    public void navigate() {
        // Navigate to the All Video Games page using the URL
        BrowserActions.navigateToURL(driver, url);
        // Refresh the page
        refreshThePage();
    }
    /**
     * Change the language to English.
     */
    public static void changeLanguage() {
        // Hover over the language button
        ElementActions.hover(driver, changeLang_button);
        // Click the English language option
        ElementActions.click(driver, englishLang_button);
    }

    /**
     * Get the `By` object for the All Side Menu icon.
     *
     * @return a `By` object representing the All Side Menu icon
     */
    public static By getAllSideMenu_icon() {
        return allSideMenu_icon;
    }


    public static void openTheSideMenu() {
        ElementActions.click(driver, allSideMenu_icon);
    }
    /**
     * Open the Sign In menu and go to the Email page.
     *
     * @return an EmailPage object representing the Email page
     */
    public static EmailPage openSignInMenu() {
        // Hover over the Sign In menu and click the Sign In button
        (new ElementActions(driver)).hover(singInMenu)
                .click(signIn_button);
        // Return an EmailPage object representing the Email page
        return new EmailPage(driver);
    }


    public static void clickOnVideoGames() {
        ElementActions.click(driver, videoGames_link);
    }

    public static By getVideoGames_link() {
        return videoGames_link;
    }

    public static AllVideGamesPage clickOnAllVideoGames() {
        ElementActions.click(driver, allVideoGames_link);
        return new AllVideGamesPage(driver);
    }

    public static void clickOnExpandMenu() {
        ElementActions.click(driver, expandSideMenu_icon);
    }

    public static By getVideoGamesPageTitle() {

        return videoGamesPageTitle;
    }

    public static By getExpandSideMenu_icon() {
        return expandSideMenu_icon;
    }

    public static boolean isNavBarDisplayed(){
        boolean navBarDisplayed = driver.findElement(nav_Bar).isDisplayed();
        System.out.println(navBarDisplayed);
        return navBarDisplayed;
    }

    public static void refreshThePage(){
        if(!isNavBarDisplayed()){

        }
    }

}
