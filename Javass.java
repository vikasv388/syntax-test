
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HandleWebPage {

    public static void main(String[] args) {

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser
            driver.manage().window().maximize();

            // Open website
            driver.get("https://www.google.com");

            // Explicit wait
            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
            );

            // Find search box
            WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.name("q")
                )
            );

            // Enter text
            searchBox.sendKeys("Java Selenium");

            // Submit search
            searchBox.submit();

            // Wait for page title
            wait.until(ExpectedConditions.titleContains("Java Selenium"));

            // Print page title
            System.out.println("Page Title: " + driver.getTitle());

            // Print current URL
            System.out.println("Current URL: " + driver.getCurrentUrl());

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // Close browser
            driver.quit();
        }
    }
}
