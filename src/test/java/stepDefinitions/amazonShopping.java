package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.amazonShoppingPage;

public class amazonShopping {
    WebDriver driver = Hooks.driver;
    amazonShoppingPage  amazonshoppingpage;


    @Given("I am on the Amazon homepage {string}")
    public void verifyAmazonHomepage(String text) {
        amazonshoppingpage = new amazonShoppingPage(driver);
        amazonshoppingpage.validatePageFooter(text);
    }

    @When("I enter {string} in search box and click search button")
    public void searchText(String text) {
        amazonshoppingpage.searchText(text);
    }

    @And("I add first product to cart with {string}")
    public void addFirstProductToCart(String quantity) {
        amazonshoppingpage.addFirstProductToCart(quantity);
    }

    @Then("I open cart and verify total price for quantity {string} for men")
    public void openCart_VerifyPriceForMen(String quantity) {
        amazonshoppingpage.openCart_VerifyPriceForMen(quantity);
    }

    @Then("I open cart and verify total price for quantity {string} for women")
    public void openCart_VerifyPriceForWomen(String quantity) {
        amazonshoppingpage.openCart_VerifyPriceForWomen(quantity);
    }

    @Then("I change the quantity for first selected item from {string} to {string}")
    public void changeQuantity(String Quantity2, String Quantity1) throws InterruptedException {
        amazonshoppingpage.changeQuantity(Quantity2, Quantity1);
    }


}
