package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LitecartCheckLogsTest {
    private EventFiringWebDriver driver;
    private WebDriverWait wait;

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }
    }

    @Before
    public void start() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new TestBase.MyListener());

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testLitecartCheckLogs() {
        // вход в админку
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // открываем каталог
        driver.findElement(By.linkText("Catalog")).click();
        // открываем все закрытые папки
        openAllFolders();

        // находим все ссылки товаров
        List<WebElement> links = driver.findElements(By.cssSelector("tr.row img ~a"));
        // создаем пустой массив для текста ссылок
        ArrayList<String> textLinks = new ArrayList<>();
        // заполняем новый массив текстами ссылок из массива links
        getTextContentFromLinks(links, textLinks);

        // пробегаемся по ссылкам и проверяем логи
        for (int i = 0; i < textLinks.size(); i++) {
            driver.findElement(By.linkText(textLinks.get(i))).click(); // нажимаем на ссылку
            driver.navigate().back(); // возвращаемся назад

            // получаем логи браузера
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if (logs.size() > 0) { // если есть хоть 1 сообщение, то выводим его на консоль
                System.out.println("При нажатии на ссылку " + textLinks.get(i)
                        + " в логе браузера появилось сообщение:");
                System.out.println(logs);
            }
        }
    }

    public void openAllFolders() {
        List<WebElement> closedFolders; // объявляем список всех закрытых папок
        // находим закрытые папки на странице
        closedFolders = driver.findElements(By.cssSelector("i[class='fa fa-folder']"));
        if (closedFolders.size() > 0) { // если найдена хотя бы одна, то выполняем цикл
            do {
                driver.findElement(By.cssSelector("i[class='fa fa-folder'] ~ a")).click();
                closedFolders = driver.findElements(By.cssSelector("i[class='fa fa-folder']"));
            } while (closedFolders.size() != 0);
        }
    }

    public void getTextContentFromLinks(List<WebElement> links, ArrayList textLinks) {
        for (int i = 0; i < links.size(); i++) {
            String a = links.get(i).getAttribute("textContent");
            textLinks.add(a);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
