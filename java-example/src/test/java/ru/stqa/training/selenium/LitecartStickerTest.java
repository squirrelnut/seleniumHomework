package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LitecartStickerTest extends TestBase {

    @Test
    public void testLitecartMenuLinks() throws InterruptedException {
        // открытие магазина litecart
        driver.get("http://localhost/litecart/en/");

        // Ищем все блоки с уточками на странице
        List<WebElement> image_wrapper = driver.findElements(By.cssSelector("div.image-wrapper"));
        System.out.println("Всего товаров = " + image_wrapper.size());

        // перебором пробегаемся по каждому блоку и ищем в нем вложенные блоки, имеющие слово "sticker" в названии класса
        for (int i=0; i<image_wrapper.size(); i++){
            List<WebElement> stickers = image_wrapper.get(i).findElements(By.cssSelector("div[class^='sticker']"));

            if (stickers.size()>1) {
                System.out.println("Товар № " + (i+1) + " имеет больше одного стикера.");
            } else if (stickers.size() == 0) {
                System.out.println("Товар № " + (i+1) + " не имеет стикера.");
            } else {
                System.out.println("Товар № " + (i+1) + " имеет один стикер.");
            }
        }
    }
}
