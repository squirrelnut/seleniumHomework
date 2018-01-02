package ru.stqa.training.selenium.pageObjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainStorePage extends Page {
    public MainStorePage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage(){
        driver.get("http://localhost/litecart/en/");
    }

    public void openFirstProduct(){
        driver.findElement(By.cssSelector("div.image-wrapper img")).click();
    }
}
