package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
//        FirefoxOptions options = new FirefoxOptions()
//                .setBinary("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")
//                .setLegacy(true);
//        driver = new FirefoxDriver(options);

        driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();
        System.out.println(((HasCapabilities) driver).getCapabilities()); // Печать в консоль настроек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
