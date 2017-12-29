package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyFirstTest extends TestBase {
    @Test
    public void getBrouserLogs() {
        driver.get("http://selenium2.ru");
        System.out.println(driver.manage().logs().getAvailableLogTypes());
//        driver.manage().logs().get("browser").getAll();
        driver.manage().logs().get("browser").forEach(l-> System.out.println(l));
    }
    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://www.yandex.ru");
        driver.findElement(By.cssSelector("input#text")).sendKeys("selenium");
        driver.findElement(By.cssSelector("div.search2__button button[type='submit']")).click();
        wait.until(WebDriver::getTitle).toString().equals("Яндекс");

    }
}
