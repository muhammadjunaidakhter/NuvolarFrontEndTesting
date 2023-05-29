package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class amazonShoppingPage {

    WebDriver driver;
    JavascriptExecutor javaScriptExecutor;
    float initialPriceForMen;
    float priceForMen;
    float initialPriceForWomen;
    float subTotal;
    float expectedPrice;

    @FindBy(xpath = "//div[@id='navFooter']/div[5]/span")
    WebElement pageFooterTextXpath;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBoxField;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(xpath = "(//div[@id='s-skipLinkTargetForMainSearchResults']/following-sibling::span/div/div[2]//img)[1]")
    WebElement firstProductXpath;

    @FindBy(xpath = "//select/following-sibling::span//span[text()='Qty:']/following-sibling::span")
    WebElement quantityBtn;

    @FindBy(xpath = "//div[@class='a-section a-spacing-micro']//span[@class='a-price-whole']")
    WebElement initialPrice1Xpath;

    @FindBy(xpath = "//div[@class='a-section a-spacing-micro']//span[@class='a-price-whole']/span")
    WebElement initialPrice2Xpath;

    @FindBy(xpath = "//div[@class='a-section a-spacing-micro']//span[@class='a-price-whole']/following-sibling::span")
    WebElement initialPrice3Xpath;

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    WebElement addToCartBtn;

    @FindBy(xpath = "//span[@class='ewc-subtotal-amount']/span")
    WebElement subTotalXpath;

    @FindBy(xpath = "//div[@data-item-index='2']/div[3]/div[7]//span[@data-action='a-dropdown-button']")
    WebElement firstQuantitSelectorAddToCart;

    public amazonShoppingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        javaScriptExecutor = (JavascriptExecutor) driver;

    }

    public void waitForWebElementToAppear(WebElement webelement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(webelement));
    }

    public void waitForWebElementToClickable(WebElement webelement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(webelement));
    }

    public void validatePageFooter(String footerText) {
        waitForWebElementToAppear(pageFooterTextXpath);
        Assert.assertEquals(pageFooterTextXpath.getText(), footerText);
    }

    public void searchText(String text) {
        waitForWebElementToAppear(searchBoxField);
        searchBoxField.sendKeys(text);
        waitForWebElementToAppear(searchButton);
        searchButton.click();
    }

    public void addFirstProductToCart(String quantity) {
        waitForWebElementToAppear(firstProductXpath);
        waitForWebElementToClickable(firstProductXpath);
        firstProductXpath.click();
        waitForWebElementToAppear(quantityBtn);
        waitForWebElementToClickable(quantityBtn);
        quantityBtn.click();
        WebElement quantitySelectorXpath = driver.findElement(By.xpath("//ul[@class='a-nostyle a-list-link']/li/a[normalize-space() = '"+quantity+"']"));
        waitForWebElementToAppear(quantitySelectorXpath);
        waitForWebElementToClickable(quantitySelectorXpath);
        quantitySelectorXpath.click();
    }

    public void openCart_VerifyPriceForMen(String quantity) {
        waitForWebElementToAppear(initialPrice1Xpath);
        waitForWebElementToAppear(initialPrice1Xpath);
        if (driver.findElements(By.xpath("//div[@class='a-section a-spacing-micro']//span[@class='a-price-whole']/following-sibling::span")).size() != 0){
            initialPriceForMen = Float.parseFloat(initialPrice1Xpath.getText()+initialPrice2Xpath.getAttribute("innerHTML")+initialPrice3Xpath.getText());
        } else {
            initialPriceForMen = Float.parseFloat(initialPrice1Xpath.getText());
        }
        System.out.println("Initial price for Men : " + initialPriceForMen);
        waitForWebElementToAppear(addToCartBtn);
        waitForWebElementToClickable(addToCartBtn);
        addToCartBtn.click();
        waitForWebElementToAppear(subTotalXpath);
        waitForWebElementToClickable(subTotalXpath);
        String value = subTotalXpath.getText().replaceAll("[^0-9.]", "");
        subTotal = Float.parseFloat(value);
        priceForMen = initialPriceForMen* Integer.parseInt(quantity);
        Assert.assertEquals(subTotal, priceForMen, 0.01);
        System.out.println("SubTotal after 1st add to card : " + subTotal);
    }

    public void openCart_VerifyPriceForWomen(String quantity) {
        waitForWebElementToAppear(initialPrice1Xpath);
        waitForWebElementToAppear(initialPrice1Xpath);
        if (driver.findElements(By.xpath("//div[@class='a-section a-spacing-micro']//span[@class='a-price-whole']/following-sibling::span")).size() != 0){
            initialPriceForWomen = Float.parseFloat(initialPrice1Xpath.getText()+initialPrice2Xpath.getAttribute("innerHTML")+initialPrice3Xpath.getText());
        } else {
            initialPriceForWomen = Float.parseFloat(initialPrice1Xpath.getText());
        }
        System.out.println("Initial price for Women : " + initialPriceForWomen);
        waitForWebElementToAppear(addToCartBtn);
        waitForWebElementToClickable(addToCartBtn);
        addToCartBtn.click();
        waitForWebElementToAppear(subTotalXpath);
        waitForWebElementToClickable(subTotalXpath);
        String value = subTotalXpath.getText().replaceAll("[^0-9.]", "");
        subTotal = Float.parseFloat(value);
        expectedPrice = initialPriceForWomen + priceForMen;
        Assert.assertEquals(subTotal, expectedPrice, 0.001);
        System.out.println("SubTotal after 2nd add to card : " + subTotal);
    }

    public void changeQuantity(String Quantity2, String Quantity1) throws InterruptedException {
        waitForWebElementToAppear(firstQuantitSelectorAddToCart);
        waitForWebElementToAppear(firstQuantitSelectorAddToCart);
        firstQuantitSelectorAddToCart.click();
        WebElement quantitySelector2Xpath = driver.findElement(By.xpath("//ul[@class='a-nostyle a-list-link']/li//input[@value = '"+Quantity2+"']"));
        waitForWebElementToAppear(quantitySelector2Xpath);
        quantitySelector2Xpath.click();

        waitForWebElementToAppear(firstQuantitSelectorAddToCart);
        waitForWebElementToAppear(firstQuantitSelectorAddToCart);
        firstQuantitSelectorAddToCart.click();
        WebElement quantitySelector1Xpath = driver.findElement(By.xpath("//ul[@class='a-nostyle a-list-link']/li//input[@value = '"+Quantity1+"']"));
        waitForWebElementToAppear(quantitySelector1Xpath);
        quantitySelector1Xpath.click();

        waitForWebElementToAppear(subTotalXpath);
        waitForWebElementToClickable(subTotalXpath);
        Thread.sleep(5000);
        String value = subTotalXpath.getText().replaceAll("[^0-9.]", "");
        subTotal = Float.parseFloat(value);

        expectedPrice = initialPriceForWomen + initialPriceForMen;
        Assert.assertEquals(subTotal, expectedPrice, 0.001);
        System.out.println("SubTotal after after last change : " + subTotal);
    }
}
