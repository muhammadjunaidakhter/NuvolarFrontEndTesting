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

    @When("I enter {string} in search box and click search icon")
    public void searchText(String text) {
        amazonshoppingpage.searchText(text);
    }

    @And("I add product to cart with {string}")
    public void addProductToCart(String quantity) {
        amazonshoppingpage.addProductToCart(quantity);
    }

    @Then("I open cart and verify total price and quantity {string} for men")
    public void openCart_VerifyPriceForMen_And_Quantity(String quantity) {
        amazonshoppingpage.openCart_VerifyPriceForMen_And_Quantity(quantity);
    }

    @Then("I open cart and verify total price for quantity {string} for women")
    public void openCart_VerifyPriceFoWorMen_And_Quantity(String quantity) {
        amazonshoppingpage.openCart_VerifyPriceFoWorMen_And_Quantity(quantity);
    }

    @Then("I change the quantity for first selected item from {string} to {string}")
    public void changeQuantity(String Quantity2, String Quantity1){
        amazonshoppingpage.changeQuantity(Quantity2, Quantity1);
    }

    @Then("I verify total price and quantity {string} are changed correctly")
    public void verifyTotalPrice_And_Quantity(String Quantity) throws InterruptedException {
        amazonshoppingpage.verifyTotalPrice_And_Quantity(Quantity);
    }


}
