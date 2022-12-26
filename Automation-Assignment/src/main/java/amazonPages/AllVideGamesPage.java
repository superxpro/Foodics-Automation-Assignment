package amazonPages;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class AllVideGamesPage {
    private static WebDriver driver;

    private static int addedCount = 0;
    private static boolean addedProduct = false;
    private final static By freeShipping_checkBox = By.xpath("//span[text()=\"Free Shipping\"]");
    private final static By freeShippingEligibleTitle = By.xpath("//span[contains(text(),'Eligible for Free Shipping')]");
    private final static By newCondition_filterOption = By.xpath("//span[text()=\"New\"]");
    private final static By itemsCondition = By.xpath("//span[contains(text(),'Any Condition')]");
    private final static By sortList = By.xpath("//span[@id='a-autoid-0-announce']");
    private final static By highToLowSort_option = By.xpath("//a[@id='s-result-sort-select_2']");
    private final static By highToLowOption_text = By.xpath("//span[contains(text(),'Price: High to Low')]");


    public AllVideGamesPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void clickOnTheFreeShipping() {
        ElementActions.click(driver, freeShipping_checkBox);
    }

    public static By getFreeShippingLabel() {
        return freeShippingEligibleTitle;
    }

    public static void clickOnTheNewFilter_Option() {
        ElementActions.click(driver, newCondition_filterOption);
    }

    public static By getItemsCondition() {
        return itemsCondition;
    }

    public static void clickOnTheSortList() {
        ElementActions.click(driver, sortList);
    }

    public static void clickOnTheHighToLowSort() {
        ElementActions.click(driver, highToLowSort_option);
    }

    public static By getHighToLowOption_text() {
        return highToLowOption_text;
    }


    /**
     * Get the number of items in the cart.
     *
     * @return the number of items in the cart
     */
    public static int getCartItemsCount() {
        // Locate the element that displays the number of items in the cart
        By itemCartCount = By.id("nav-cart-count");
        String itemCountText = driver.findElement(itemCartCount).getText();

        // Convert the item count text to an integer and return it
        int itemCount = Integer.parseInt(itemCountText);
        return itemCount;
    }

    /**
     * Empty the cart before running a test.
     */
    public static void emptyTheCartBeforeTest() {
        // Check if the cart is not empty
        if (getCartItemsCount() > 0) {
            // Locate the cart button and click it
            By cart_button = By.id("nav-cart-count");
            ElementActions.click(driver, cart_button);

            // Locate the items in the cart
            while (true) {
                // Locate the items in the cart
                List<WebElement> items = driver.findElements(By.xpath("//input[@value='Delete']"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                // If there are no more items in the cart, break out of the loop
                if (items.isEmpty()) {
                    break;
                }

                // Locate the delete button for the first item
                WebElement deleteButton = items.get(0);

                // Click the delete button
                ElementActions.click(driver, By.xpath("//input[@value='Delete']"));
            }
        }
    }

    /**
     * Add products to the cart until the total cost reaches 15000 or we have added at least 3 products.
     */
    public static void addProductToCart() {
        // Initialize variables to track the added count and total cost
        int addedCount = 0;
        int totalCost = 0;
        int goToNextPageCounter = 0;

        // Loop until the total cost reaches 15000 or we have added at least 3 products
        do {
            // Execute JavaScript to ensure that the page has fully loaded
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("return window.performance.timing.loadEventEnd > 0");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

            // Get a list of all the products on the page
            List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));

            // Loop through each product
            for (WebElement product : products) {
                // Locate the price element and extract the price
                List<WebElement> priceElements = product.findElements(By.xpath(".//span[@class='a-price-whole']"));
                if (priceElements.size() > 0) {
                    WebElement priceElement = priceElements.get(0);
                    String priceText = priceElement.getText();
                    // Remove the comma from the string
                    priceText = priceText.replace(",", "");
                    // Parse the cleaned string as a double value
                    int price = Integer.parseInt(priceText);

                    // If the price is under 15000, open the product link in a new tab and add it to the cart
                    if (totalCost + price < 15000) {
                        // The total cost would exceed 15000, so skip this product
                        WebElement productLink = product.findElement(By.xpath(".//*[contains(@class, 'a-size-mini a-spacing-none a-color-base s-line-clamp-2')]"));

                        WebElement itemLink = product.findElement(By.xpath(".//*[contains(@class, 'a-size-mini a-spacing-none a-color-base s-line-clamp-2')]//child::a"));
                        BrowserActions.switchToNewTab(driver, itemLink.getAttribute("href"));

                        ElementActions.click(driver, By.xpath("//input[@id='add-to-cart-button']"));

                        String newWindowHandle = driver.getWindowHandles().stream().filter(handle -> !handle.equals(driver.getWindowHandle())).findFirst().get();
                        driver.switchTo().window(newWindowHandle);

                        // Increment the added count and add the price to the total cost
                        addedCount++;
                        totalCost += price;
                    }

                }
            }
            // If we have added less than 3 products, go to the next page
            if (addedCount < 3) {
                // Call the goToNextPage method to navigate to the next page
                goToNextPage();
                // Increment the goToNextPageCounter
                goToNextPageCounter++;
            }

            // If goToNextPage has been called more than 10 times, exit the loop
            if (goToNextPageCounter > 10) {
                break;
            }
            // Continue looping until addedProduct is true or addedCount is greater than or equal to 3
        } while (!addedProduct && addedCount < 3);
        // Refresh the current page
        BrowserActions.refreshCurrentPage(driver);
    }

    /**
     * Check if the cart meets the following conditions:
     * - Total price is less than 15000
     * - Number of items is more than 1 but less than 3
     *
     * @return true if the conditions are met, false otherwise
     */
    public static boolean checkItemsInTheCart() {
        // Get the number of items in the cart
        int itemCount = getCartItemsCount();
        // Get the total price of the items in the cart
        int price = getCartTotalPrice();
        // Return true if the total price is less than 15000 and the item count is more than 1 but less than 3
        return price < 15000 && itemCount > 1 && itemCount < 3;
    }

    /**
     * Get the total price of the items in the cart.
     *
     * @return the total price as an integer
     */
    public static int getCartTotalPrice() {
        // Locate the element that displays the subtotal amount
        By subTotalAmount = By.id("sc-subtotal-amount-activecart");
        // Get the text of the element
        String priceText = driver.findElement(subTotalAmount).getText();
        // Remove the currency symbol and the comma from the string
        priceText = priceText.replace("EGP", "").replace(",", "");

        // Remove the extra spaces at the beginning and the end of the string
        priceText = priceText.trim();

        // Remove the .00 at the end of the string
        priceText = priceText.replace(".00", "");

        // Parse the cleaned string as an integer value
        int price = Integer.parseInt(priceText);
        // Return the price
        return price;
    }

    /**
     * Go to the next page of search results.
     */
    public static void goToNextPage() {
        // Locate the "Next" button
        By nextButton = By.xpath("//a[text()='Next']");
        // Click the "Next" button
        ElementActions.click(driver, By.xpath("//a[text()='Next']"));
    }

    /**
     * Open the cart page.
     *
     * @return a CartPage object representing the cart page
     */
    public static CartPage openCartPage() {
        // Click the cart button to open the cart page
        ElementActions.click(driver, By.xpath("//div[@id='nav-cart-count-container']"));
        // Return a new CartPage object representing the cart page
        return new CartPage(driver);
    }


}

