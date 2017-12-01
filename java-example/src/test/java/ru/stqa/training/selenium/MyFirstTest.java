package ru.stqa.training.selenium;

import com.google.common.collect.ImmutableMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        // вызов драйверов браузера
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();

        // вызов Firefox по старой схеме
        FirefoxOptions options = new FirefoxOptions()
                .setBinary("C:\\Program Files\\Mozilla Firefox ESR45\\firefox.exe")
                .setLegacy(true);
        driver = new FirefoxDriver(options);

//        FirefoxOptions options = new FirefoxOptions()
//                .setBinary("C:\\Program Files\\Mozilla Firefox ESR45\\firefox.exe")
//                .addArguments("-console")
//                .addPreference("browser.cache.disk.enable", false)
//                .addCapabilities(new DesiredCapabilities(
//                        ImmutableMap.of("pageLoadStrategy", "eager")));

//        driver = new FirefoxDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());


//        driver = new FirefoxDriver(
//                new FirefoxBinary(
//                        new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")),
//                        new FirefoxProfile(),
//                        caps);
//
//        System.out.println();
//        );

        // вызов Nightly
//        driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://www.yandex.ru");

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
