package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class LitecartStoreProductCheck extends TestBase {
    @Test
    public void testLitecartStoreProductCheck(){
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("#box-campaigns img")).click();
        driver.findElement(By.tagName("h1")).click();

    }
}
