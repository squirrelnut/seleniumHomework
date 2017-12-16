package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LitecartNewProductTest extends TestBase {
    @Test
    public void testLitecartNewProductTest() throws IOException {
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
        fillInput("input[name='name[en]']","Life Ring"); //  название товара
        fillInput("input[name='code']", "lr001"); //  код товара
        putCheckbox("input[name='categories[]'][data-name='Root']"); //  чек-бокс Root
        fillSelect("select[name='default_category_id']", "Root"); // выбираем Root
        putCheckbox("input[name='product_groups[]'][value='1-3']");//  чек-бокс Unisex
        fillInput("input[name='quantity']", "10");
        fillSelect("select[name='quantity_unit_id']", "pcs");
        fillSelect("select[name='delivery_status_id']", "3-5 days");
        fillSelect("select[name='sold_out_status_id']", "Sold out");
        attachFile("input[name='new_images[]']", "img\\lifering.png");  // Прикрепляем файл
        // заполняем даты
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("2017-12-12");
        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("2018-12-12");

        //Переходим на вкладку Information и заполняем ее
        driver.findElement(By.linkText("Information")).click();
        pause(2000); // пауза
        fillSelect("select[name='manufacturer_id']", "ACME Corp.");
        fillInput("input[name='short_description[en]']", "Life ring is means to assist someone who is drowning.");
        fillTextarea("textarea[name='description[en]']");


        //Переходим на вкладку Information и заполняем ее
        driver.findElement(By.linkText("Prices")).click();
        pause(2000); // пауза
        fillInput("input[name='purchase_price']", "100");
        fillSelect("select[name='purchase_price_currency_code']", "US Dollars");
        fillInput("input[name='prices[USD]']", "100");

        // Сохраняем новый товар
        driver.findElement(By.cssSelector("button[name='save']")).click();

        // Проверяем, что новый товар появился
        driver.findElement(By.linkText("Life Ring")).click();

    }

    public void putCheckbox(String locator) {
        WebElement checkbox = driver.findElement(By.cssSelector(locator));
        if (checkbox.isSelected()) {
            return;
        } else {
            checkbox.click();
        }
    }

    public void fillInput(String locator, String text) {
        WebElement element = driver.findElement(By.cssSelector(locator));
        element.clear();
        element.sendKeys(text);

    }

    public void fillSelect(String locator, String selectedItem) {
        WebElement selectElement = driver.findElement(By.cssSelector(locator));
        Select select = new Select(selectElement);
        //List options = select.getOptions();
        select.selectByVisibleText(selectedItem);
    }

    public void attachFile(String locaror, String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            driver.findElement(By.cssSelector(locaror)).sendKeys(file.getAbsolutePath());
            System.out.println("Файл " + file.getAbsolutePath() + " прикреплен");
        } else {
            System.out.println("Файл " + file.getAbsolutePath() + " не существует");
        }


    }

    public void pause(Integer milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fillTextarea(String locator) {
        WebElement element = driver.findElement(By.cssSelector(locator));
        element.clear();
        String text = "Life ring is designed to be thrown to a person in the water, to provide buoyancy and prevent drowning.";
        element.sendKeys(text);

    }
}
