package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.run.WebDriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AmazonSteps {
    private WebDriver driver =null;
    WebDriverWait wait = null;
    @Before
    public void createDriver(){
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void quit(){
        driver.quit();
    }

    @Given("I go to the Amazon")
    public void gotoAmazon(){
        driver.get("https://www.amazon.com");
    }

    @And("I search Nikon")
    public void searchNikon(){
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Nikon");
        searchBox.submit();

    }

    @And("I sort the result from highest price to slowest")
    public void sortResult(){
        // Find and click the "Sort by" dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("a-dropdown-prompt")));
        WebElement sortByDropdown = driver.findElement(By.className("a-dropdown-prompt"));
        sortByDropdown.click();

        // Select "Price: High to Low" from the dropdown
        WebElement priceHighToLowOption = driver.findElement(By.id("s-result-sort-select_2"));
        priceHighToLowOption.click();
    }

    @When("I click the 2nd result")
    public void clickResult(){
        // Click The second product image
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("s-image")));
        List<WebElement> images = driver.findElements(By.className("s-image"));
        images.get(1).click();
    }

    @Then("I can see the product")
    public void seeResult(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("productTitle")));
        String title = driver.findElement(By.id("productTitle")).getText();
        Assert.assertTrue(title.contains(""));
    }

}
