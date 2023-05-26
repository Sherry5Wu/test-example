package org.example;

import java.time.Duration;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
*/

/**
 * The test case is for testing:
 * 1.open amazon.com,and search for "Nikon"; 
 * 2.Sort products by price descending; 
 * 3.choose the second one from the list; 
 * 4.verify if the "Nikon D3X" is contained in the product title. *
 */

public class AmazonTest {
	
   	private WebDriver driver;
	private WebDriverWait wait;

    @Before
    public void setUp() {
        // Set webdrive paths of browsers
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", "path/to/geckodriver-v0.33.0-win-aarch64.exe");
       

        // Initialize ChromeDriver
        driver = new ChromeDriver();    
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
    }

    @Test
    public void testAmazon() {
        // Test with Chrome browser
        performTest(driver);
        driver.manage().window().maximize();
        
        // Close the Chrome browser
        driver.quit();

       /**
        // Initialize FirefoxDriver        
    	String firefoxBinaryPath = "C:/Program Files/Mozilla Firefox/firefox.exe";
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefoxBinaryPath);
        driver = new FirefoxDriver(options);
      
        // Test with Firefox browser
       performTest(driver);
       */
    }

    public void performTest(WebDriver driver) {
        // Step1: open amazon website
        driver.get("https://www.amazon.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // step2: search for "Nikon"
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nikon");
        driver.findElement(By.id("nav-search-submit-button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("a-dropdown-prompt")));
        
        // step3: sort products by price in descending order
        driver.findElement(By.className("a-dropdown-prompt")).click();
        driver.findElement(By.id("s-result-sort-select_2")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("s-image")));

        // Step4: Select the second product and click it
        List<WebElement> images = driver.findElements(By.className("s-image"));
        images.get(1).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));

        // Step5: Verify that the product topic contains "Nikon D3X"
        String productTitle = driver.findElement(By.id("productTitle")).getText();
        String expectedText = "Nikon D3X";
        assert productTitle.contains(expectedText) : "Product title does not contain 'Nikon D3X'.";
        
    }

    @After
    public void tearDown() {
        // Quit the browser
        driver.quit();
    }
}

