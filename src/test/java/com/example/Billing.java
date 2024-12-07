package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Billing {

    private WebDriver driver;
    private WebDriverWait wait;

    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/FRAFA-PC/automation/selenium-billing/driver/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String username, String password) {
        driver.get("https://www.saucedemo.com/");
        WebElement usrInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user-name")));
        WebElement pwdInput = driver.findElement(By.cssSelector("#password"));

        usrInput.sendKeys(username);
        pwdInput.sendKeys(password);
        pwdInput.sendKeys(Keys.RETURN);
    }

    public void addToBasket() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-to-cart-sauce-labs-backpack")));
        WebElement basketBtn = driver.findElement(By.cssSelector("#shopping_cart_container > a"));

        addBtn.click();
        basketBtn.click();
    }

    public void validBasket() {
        try {
            WebElement validBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout")));
            validBtn.click();

            WebElement firstInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#first-name")));
            WebElement secondInput = driver.findElement(By.cssSelector("#last-name"));
            WebElement postalInput = driver.findElement(By.cssSelector("#postal-code"));

            firstInput.sendKeys("Julius");
            secondInput.sendKeys("Konan");
            postalInput.sendKeys("12345");

            WebElement validBtn2 = driver.findElement(By.cssSelector("#continue"));
            validBtn2.click();

            WebElement endBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#finish")));
            endBtn.click();

            WebElement goHome = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#back-to-products")));
            goHome.click();
        } catch (Exception e) {
            System.err.println("Error during basket validation: " + e.getMessage());
        }
    }

    public void logout() {
        try {
            WebElement menuIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#react-burger-menu-btn")));
            menuIcon.click();

            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
            logoutBtn.click();
        } catch (Exception e) {
            System.err.println("Error during logout: " + e.getMessage());
        }
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Main method to execute the test
    public static void main(String[] args) {
        Billing test = new Billing();
        try {
            test.setup();
            test.login("standard_user", "secret_sauce");
            test.addToBasket();
            test.validBasket();
            test.logout();
        } finally {
            test.teardown();
        }
    }
}
