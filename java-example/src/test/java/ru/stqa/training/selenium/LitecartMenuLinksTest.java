package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LitecartMenuLinksTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        FirefoxOptions options = new FirefoxOptions()
                .setBinary("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")
                .setLegacy(true);
        driver = new FirefoxDriver(options);

        System.out.println(((HasCapabilities) driver).getCapabilities()); // Печать в консоль настроек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testLitecartMenuLinks() throws InterruptedException {
        // открытие админки и вход в нее
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // проверка ссылок
        driver.findElement(By.linkText("Appearence")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-template")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-logotype")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-catalog")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-product_groups")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-option_groups")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-manufacturers")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-suppliers")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-delivery_statuses")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-sold_out_statuses")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-quantity_units")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-csv")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Countries")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Currencies")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Customers")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-customers")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-csv")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-newsletter")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Geo Zones")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Languages")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-languages")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-storage_encoding")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Modules")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-jobs")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-customer")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("body-wrapper")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-payment")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-order_total")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-order_success")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-order_action")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Orders")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-orders")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-order_statuses")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Pages")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Reports")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-monthly_sales")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-most_sold_products")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-most_shopping_customers")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Settings")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-store_info")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-defaults")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-general")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-listings")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-images")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-checkout")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-advanced")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-security")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Slides")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Tax")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-tax_classes")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-tax_rates")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Translations")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-search")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-scan")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-csv")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("Users")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.linkText("vQmods")).click();
        driver.findElement(By.tagName("h1"));

        driver.findElement(By.id("doc-vqmods")).click();
        driver.findElement(By.tagName("h1"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
