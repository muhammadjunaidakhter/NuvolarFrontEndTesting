package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
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

        driver.get("https://www.amazon.com");


    }

    @After
    public void tearDown() {
        // Close the browser
        driver.quit();
    }



}

