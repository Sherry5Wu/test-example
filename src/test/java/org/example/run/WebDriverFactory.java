package org.example.run;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    public static WebDriver createWebDriver() {
        String webdriver = System.getProperty("browser", "chrome");
        switch (webdriver) {
            case "firefox" -> {
                return new FirefoxDriver();
            }
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
                return new ChromeDriver();
            }
            default -> throw new RuntimeException("Unsupported webdriver: " + webdriver);
        }
    }
}