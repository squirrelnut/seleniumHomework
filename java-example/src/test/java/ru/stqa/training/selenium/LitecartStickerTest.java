package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LitecartStickerTest {
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
// --------------------------------------------------------
        // находим все блоки класса "image-wrapper" и записываем в массив вложенные в них div-ы
//        List<WebElement> ducks = driver.findElements(By.cssSelector(".image-wrapper>.div"));

//        if (ducks.isEmpty() == false){ // если список не пустой
//            // то проверяем, что вложенный div у всех уток имеет в своем названии слово "sticker"
//            stickerCheck(ducks);
//        }
// --------------------------------------------------------

        // открытие магазина litecart
        driver.get("http://localhost/litecart/en/");

        // Ищем все блоки с уточками на странице
        List<WebElement> image_wrapper = driver.findElements(By.cssSelector(".image-wrapper"));
        System.out.println("Всего уток = " + image_wrapper.size());

        // перебором пробегаемся по каждому блоку с уточкой и ищем в нем все вложенные блоки
        for (int i=0; i<image_wrapper.size(); i++){
            List<WebElement> stickers = image_wrapper.get(i).findElements(By.tagName("div"));

            if (stickers.size()>1) {
                System.out.println("У уточки под номером " + (i+1) + " больше одного стикера");
            } else if (stickers.size() == 0) {
                System.out.println("У уточки под номером " + (i+1) + " нет стикера");
            } else {
                String atr = stickers.get(0).getAttribute("class");
                boolean hasSticker = atr.contains("sticker");
                if (hasSticker) {
                    System.out.println("У уточки под номером " + (i+1) + " один стикер.");
                }
            }
        }


    }

    public void stickerCheck( List<WebElement> ducks) {
        for (int i=0; i<ducks.size(); i++) {
            // получаем содержимое атрибута class
            String atr = ducks.get(i).getAttribute("class");

            // сравниваем, что имя класса имеет в своем назывании слово "sticker"
            boolean hasSticker = atr.contains("sticker");

            if (hasSticker == false) {
                System.out.println("У уточки под номером " + (i+1) + " нет стикера.");
            } else {

            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
