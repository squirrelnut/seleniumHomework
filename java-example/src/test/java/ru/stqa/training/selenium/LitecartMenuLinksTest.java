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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
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
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 0);
    }

    @Test
    public void testLitecartMenuLinks() throws InterruptedException {
        // открытие админки и вход в нее
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // находим все ссылки пунктов меню
        List<WebElement> countriesLinks = driver.findElements(By.cssSelector("ul#box-apps-menu li#app- .name"));

        // создаем пустой массив для текста ссылок
        ArrayList<String> linksText = new ArrayList<>();

        // пробегаемся по массиву countriesLinks и записываем в массив linksText все тексты ссылок
        getAttributeFromArray(countriesLinks, linksText, "textContent");

        // пробегаемся по всем страницам и проверяем, что в них есть заголовок h1
        checkPages(linksText);

    }

    public void getAttributeFromArray(List<WebElement> webElementsArray, ArrayList attributeArray, String attribute) {
        for (int i = 0; i < webElementsArray.size(); i++) {
            String a = webElementsArray.get(i).getAttribute(attribute);
            attributeArray.add(a);
        }
    }


    public void checkPages(ArrayList<String> arrayList){
        for (int i = 0; i < arrayList.size(); i++) {
            driver.findElement(By.linkText(arrayList.get(i))).click();
            System.out.println("На странице " + arrayList.get(i) + " " + checkH1());

            // проверяем есть ли вложенные пункты меню
            List<WebElement> insideLinks = driver.findElements(By.cssSelector("ul#box-apps-menu li#app- .docs .name"));
            // если есть, то снова получаем тексты сслок и затем пробегаемся по ним.
            if (insideLinks.size() > 0) {
                ArrayList<String> insideLinksText = new ArrayList<>();
                getAttributeFromArray(insideLinks, insideLinksText, "textContent");
                for(int k=0;k<insideLinksText.size(); k++){
                    driver.findElement(By.linkText(insideLinksText.get(k))).click();
                    System.out.println("На странице " + insideLinksText.get(k) + " " + checkH1());
                }
            }
        }


    }


    public String checkH1(){
        if(driver.findElement(By.tagName("h1")).isDisplayed() == true){
            return "есть заголовок";
        } else {
            return "нет заголовка";
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
