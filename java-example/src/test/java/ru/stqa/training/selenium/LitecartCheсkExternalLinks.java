package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class LitecartCheсkExternalLinks extends TestBase {

    @Test
    public void testLitecartCheсkExternalLinks() {
        // вход в админку
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // откываем раздел Countries
        driver.findElement(By.linkText("Countries")).click();
        // нажимаем кнопку добавления новой страны
        driver.findElement(By.cssSelector("a.button")).click();

        // получаем все ссылки
        List<WebElement> links = driver.findElements(By.cssSelector("i[class='fa fa-external-link']"));

        // получаем id текущего окна
        String mainWindow = driver.getWindowHandle();

        // пробегаемся по ссылкам
        for (int i = 0; i < links.size(); i++) {
            // получаем id всех открытых в данный момент окон
            Set<String> oldWindows = driver.getWindowHandles();

            // нажимаем на ссылку
            links.get(i).click();

            // ждем пока откроется новое окно. (подсмотрела здесь - http://internetka.in.ua/selenium-webdriver-window/, сама бы вряд ли додумалась...)
            String newWindow = (new WebDriverWait(driver, 10))
                    .until(new ExpectedCondition<String>() {
                               public String apply(WebDriver driver) {
                                   Set<String> newWindowsSet = driver.getWindowHandles();
                                   newWindowsSet.removeAll(oldWindows);
                                   return newWindowsSet.size() > 0 ?
                                           newWindowsSet.iterator().next() : null;
                               }
                           }
                    );

            // переключаемся на новое окно
            driver.switchTo().window(newWindow);
            driver.close(); // закрываем его

            // возвращаемся к старому окну
            driver.switchTo().window(mainWindow);
        }
    }

}
