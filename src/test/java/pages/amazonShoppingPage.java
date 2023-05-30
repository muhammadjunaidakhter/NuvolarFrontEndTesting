package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

public class amazonShoppingPage {

    WebDriver driver;
    JavascriptExecutor javaScriptExecutor;
    float actualPriceForMen;
    float priceForMen;
    float actualPriceForWomen;
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
    WebElement actualPrice1Xpath;

    @FindBy(xpath = "//div[@class='a-section a-spacing-micro']//span[@class='a-price-whole']/span")
    WebElement actualPrice2Xpath;

    @FindBy(xpath = "//div[@class='a-section a-spacing-micro']//span[@class='a-price-whole']/following-sibling::span")
    WebElement actualPrice3Xpath;

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
        //Enter text in search box
        waitForWebElementToAppear(searchBoxField);
        searchBoxField.sendKeys(text);
        //Click search box icon
        waitForWebElementToAppear(searchButton);
        searchButton.click();
    }

    public void addProductToCart(String quantity) {
        //Wait for the first product appear after clicking search box icon
        waitForWebElementToAppear(firstProductXpath);
        waitForWebElementToClickable(firstProductXpath);
        firstProductXpath.click();
        waitForWebElementToAppear(quantityBtn);
        waitForWebElementToClickable(quantityBtn);
        //Select quantity of product
        quantityBtn.click();
        WebElement quantitySelectorXpath = driver.findElement(By.xpath("//ul[@class='a-nostyle a-list-link']/li/a[normalize-space() = '"+quantity+"']"));
        waitForWebElementToAppear(quantitySelectorXpath);
        waitForWebElementToClickable(quantitySelectorXpath);
        quantitySelectorXpath.click();
    }

    public void openCart_VerifyPriceForMen_And_Quantity(String quantity) {
        Integer selectedQuantity;
        Integer expectedQuantity = Integer.parseInt(quantity);

        //Get actual price of product
        actualPriceForMen = getActualPrice();
        System.out.println("Initial price for Men : " + actualPriceForMen);

        //Click add to cart button
        waitForWebElementToAppear(addToCartBtn);
        waitForWebElementToClickable(addToCartBtn);
        addToCartBtn.click();

        //Get subtotal after adding product to cart and verify
        subTotal = getSubTotal();
        priceForMen = actualPriceForMen* expectedQuantity;
        Assert.assertEquals(subTotal, priceForMen, 0.01);
        System.out.println("SubTotal after 1st add to card : " + subTotal);

        //Get selected product quantity and verify
        selectedQuantity = getQuantityOf_FirstProduct();
        Assert.assertEquals(expectedQuantity, selectedQuantity);

        System.out.println("Selected quantity : " + selectedQuantity);
    }

    public Float getActualPrice(){
        Float price;
        //Product floating price is in different spans, so I get it through 3 Xpath
        waitForWebElementToAppear(actualPrice1Xpath);
        waitForWebElementToAppear(actualPrice1Xpath);
        if (driver.findElements(By.xpath("//div[@class='a-section a-spacing-micro']//span[@class='a-price-whole']/following-sibling::span")).size() != 0){
            price = Float.parseFloat(actualPrice1Xpath.getText()+actualPrice2Xpath.getAttribute("innerHTML")+actualPrice3Xpath.getText());
        } else {
            price = Float.parseFloat(actualPrice1Xpath.getText());
        }
        return price;
    }

    public Float getSubTotal(){
        //Return the subtotal after adding product to cart
        Float subTotalValue;
        waitForWebElementToAppear(subTotalXpath);
        waitForWebElementToClickable(subTotalXpath);
        String value = subTotalXpath.getText().replaceAll("[^0-9.]", "");
        subTotalValue = Float.parseFloat(value);
        return subTotalValue;
    }

    public Integer getQuantityOf_FirstProduct(){
        //Return the selected quantity of first product
        Integer openCartquantity = null;
        WebElement openCart_First_QuantityXpath = driver.findElement(By.xpath("//div[@data-item-index='1']/div[3]/div[7]//span[@data-action='a-dropdown-button']/span[2]"));
        return openCartquantity = Integer.parseInt(openCart_First_QuantityXpath.getText());
    }

    public void openCart_VerifyPriceFoWorMen_And_Quantity(String quantity) {
        Integer selectedQuantity;
        Integer expectedQuantity = Integer.parseInt(quantity);

        //Get actual price of product
        actualPriceForWomen = getActualPrice();
        System.out.println("Initial price for Men : " + actualPriceForWomen);

        //Click add to cart button
        waitForWebElementToAppear(addToCartBtn);
        waitForWebElementToClickable(addToCartBtn);
        addToCartBtn.click();

        //Get subtotal after adding product to cart and verify
        subTotal = getSubTotal();
        actualPriceForWomen = actualPriceForWomen * expectedQuantity;
        expectedPrice = actualPriceForWomen + priceForMen;
        Assert.assertEquals(subTotal, expectedPrice, 0.001);
        System.out.println("SubTotal after 2nd add to card : " + subTotal);

        //Get selected product quantity and verify
        selectedQuantity = getQuantityOf_FirstProduct();
        Assert.assertEquals(expectedQuantity, selectedQuantity);

        System.out.println("Selected quantity : " + selectedQuantity);
    }

    public void changeQuantity(String Quantity2, String Quantity1) {

        waitForWebElementToAppear(firstQuantitSelectorAddToCart);
        waitForWebElementToAppear(firstQuantitSelectorAddToCart);
        firstQuantitSelectorAddToCart.click();
        WebElement quantitySelector1Xpath = driver.findElement(By.xpath("//ul[@class='a-nostyle a-list-link']/li//input[@value = '"+Quantity1+"']"));
        waitForWebElementToAppear(quantitySelector1Xpath);
        quantitySelector1Xpath.click();

    }

    public void verifyTotalPrice_And_Quantity(String Quantity) throws InterruptedException {

        Integer selectedQuantity;
        Integer expectedQuantity = Integer.parseInt(Quantity);

        Thread.sleep(5000);
        subTotal = getSubTotal();;

        expectedPrice = actualPriceForMen + actualPriceForWomen;
        Assert.assertEquals(subTotal, expectedPrice, 0.001);
        System.out.println("SubTotal after after quantity change : " + subTotal);

        selectedQuantity = getQuantityOf_FirstProduct();
        Assert.assertEquals(expectedQuantity, selectedQuantity);

        System.out.println("Changed quantity of first product : " + selectedQuantity);
    }
}
