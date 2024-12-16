package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginClass {

    private WebDriver driver;
    private WebDriverWait wait;

    // Sélecteurs CSS
    // private static final String LIST_TAB_SELECTOR = "#block-gin-primary-local-tasks > nav > ul > li.tabs__tab.js-tab.is-active.js-active-tab > a";
    // private static final String ADD_LIST_BUTTON_SELECTOR = "#block-gin-local-actions > ul > li > a";
    // private static final String TITLE_INPUT_SELECTOR = "#edit-label-0-value";
    // private static final String MEMBER_CHECKBOX_SELECTOR = "#edit-field-membres .form-item--field-membres-4 label span.views-field-name";
    // private static final String VALIDATE_BUTTON_SELECTOR = "#edit-submit";
    // private static final String MODIFY_BUTTON_SELECTOR = "#block-gin-content > div > div > table > tbody > tr:nth-child(8) > td:nth-child(6) .edit a";
    // private static final String DELETE_BUTTON_SELECTOR = "#block-gin-content > div > div > table > tbody > tr:nth-child(8) > td:nth-child(6) .dropbutton-action.secondary-action ul > li > a";
    @BeforeClass 
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }   
    @Test
    @Parameters({"username", "password"})
    public void login(String username, String password) {
        driver.get("https://www.saucedemo.com/");
        WebElement usrInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user-name")));
        WebElement pwdInput = driver.findElement(By.cssSelector("#password"));

        usrInput.sendKeys(username);
        pwdInput.sendKeys(password);
        pwdInput.sendKeys(Keys.RETURN);
    }
    @AfterClass
    public void turnDown(){
        if(driver !=null){
            driver.quit();
        }
    }

    // public static void main(String[] args){
    //     LoginClass test =new LoginClass();
    //     try{
    //         test.setup();
    //         test.login("standard_user", "secret_sauce");
    //     }catch(Exception e){
            
    //     }
    // }
}
