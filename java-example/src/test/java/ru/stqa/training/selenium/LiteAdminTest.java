package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LiteAdminTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        // вызов драйверов браузеров по новой схеме
        //driver = new InternetExplorerDriver();
      //  driver = new FirefoxDriver();
       // driver = new ChromeDriver();

        //Вызов Firefix по старой схеме:
        FirefoxOptions options = new FirefoxOptions()
                .setBinary("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")
                .setLegacy(true);
        driver = new FirefoxDriver(options);



       // Вызов Firefox Nightly по новой схеме:
//        FirefoxOptions options = new FirefoxOptions()
//                .setBinary("C:\\Program Files\\Nightly\\firefox.exe")
//                .setLegacy(false);
//        driver = new FirefoxDriver(options);


        System.out.println(((HasCapabilities) driver).getCapabilities()); // Печать в консоль настроек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
