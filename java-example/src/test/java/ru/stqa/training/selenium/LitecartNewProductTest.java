package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LitecartNewProductTest extends TestBase {
    @Test
    public void testLitecartNewProductTest() {
        // Входим в админку
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // Открываем страницу Catalog и нажимаем кнопку добавления нового товара
        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.linkText("Add New Product")).click();

        // заполняем вкладку General
        driver.findElement(By.cssSelector("label input[value='1']")).click(); // выбираем Enabled
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys("Life Ring"); // название товара
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys("lr001");
        putCheckbox("input[name='categories[]'][data-name='Root']"); // выбираем чек-бокс Root
        fillSelect("select[name='default_category_id']","Root"); // в выпад. списке выбираем Root
        putCheckbox("input[name='product_groups[]'][value='1-3']");// выбираем чек-бокс Unisex
        driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys("10");
        fillSelect("select[name='quantity_unit_id']", "pcs");
        fillSelect("select[name='delivery_status_id']", "3-5 days");
        fillSelect("select[name='sold_out_status_id']", "Sold out");

        driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys("lifering.png");
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("12122017");
        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("12122018");

    }

    public void putCheckbox(String locator){
         WebElement checkbox = driver.findElement(By.cssSelector(locator));
         if (checkbox.isSelected()){
            return;
        } else {
            checkbox.click();
        }
    }

    public void fillSelect(String locator, String selectedItem){
        WebElement selectElement = driver.findElement(By.cssSelector(locator));
        Select select = new Select(selectElement);
        //List options = select.getOptions();
        select.selectByVisibleText(selectedItem);
    }
}
