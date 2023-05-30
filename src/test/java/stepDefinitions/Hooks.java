package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    public static WebDriver driver;
    // Set the browser name
    String browserName = "chrome";

    @Before
    public void setUp() {

        // Download and setup the appropriate driver binaries
        WebDriverManager.getInstance(browserName).setup();
        // Create the WebDriver instance
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            // Maximize the browser window
            driver.manage().window().maximize();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new ChromeDriver();
            // Maximize the browser window
            driver.manage().window().maximize();
        } else {
            throw new IllegalArgumentException("Invalid browser name");
        }

//        driver.get("https://www.amazon.com");
        driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=900&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fyourstore%2Fhome%3Fpath%3D%252Fgp%252Fyourstore%252Fhome%26signIn%3D1%26useRedirectOnSuccess%3D1%26action%3Dsign-out%26ref_%3Dnav_AccountFlyout_signout&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
        WebElement userNameXpath = driver.findElement(By.id("ap_email"));
        userNameXpath.sendKeys("malikjunaid896@gmail.com");
        WebElement continueBtnXpath = driver.findElement(By.id("continue"));
        continueBtnXpath.click();
        WebElement passwordXpath = driver.findElement(By.id("ap_password"));
        passwordXpath.sendKeys("Ktm123!");
        WebElement signInBtn = driver.findElement(By.id("signInSubmit"));
        signInBtn.click();
    }

    @After
    public void tearDown() {
        // Close the browser
//        driver.quit();
    }



}

