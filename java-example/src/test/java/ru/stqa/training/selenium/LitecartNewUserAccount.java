package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.util.List;

public class LitecartNewUserAccount extends TestBase {

    @Test
    public void testLitecartNewUserAccount(){
        // главная страница
        driver.get("http://localhost/litecart/en/");

        // страница создания нового аккаунта пользователя
        driver.findElement(By.linkText("New customers click here")).click();

        // заполняем поля
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Ivan");
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Ivanov");
        driver.findElement(By.cssSelector("input[name='address1']")).sendKeys("Address");
        driver.findElement(By.cssSelector("input[name='postcode']")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[name='city")).sendKeys("Moscow");

        // выбираем "United States" и "California" в списке стран и зон соответственно
        fillSelect("select[name='country_code']", "United States");
        fillSelect("select[name='zone_code']", "California");

        // создаем уникальный email пользователя и заполняем его
        String email = generateUniqueNumber() + "@gmail.com";
        System.out.println(email);
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("+71234567890");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("password");
        driver.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys("password");
        driver.findElement(By.cssSelector("button[name='create_account']")).click();

        // выходим из аккаунта
        driver.findElement(By.linkText("Logout")).click();

        // делаем вход в только что созданный аккаунт пользователя
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("password");
        driver.findElement(By.cssSelector("button[name='login']")).click();

        // еще раз выходим из аккаунта
        driver.findElement(By.linkText("Logout")).click();
    }

    public void fillSelect(String locator, String selectedItem){
        WebElement selectElement = driver.findElement(By.cssSelector(locator));
        Select select = new Select(selectElement);
        List options = select.getOptions();
        select.selectByVisibleText(selectedItem);
    }

    public String generateUniqueNumber(){
        Date date = new Date();
        long millis = date.getTime();
        return String.valueOf(millis);
    }
}
