package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBase {
    public EventFiringWebDriver driver;
    public WebDriverWait wait;


    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by +  " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }
    }

    @Before
    public void start() {
//        FirefoxOptions options = new FirefoxOptions()
//                .setBinary("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")
//                .setLegacy(true);
//        driver = new FirefoxDriver(options);
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();

        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());

//        System.out.println(((HasCapabilities) driver).getCapabilities()); // Печать в консоль настроек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
