package ru.stqa.training.selenium.pageObjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void removeProductAndCheckTotal() {
        // получаем все продукты из итоговой таблицы
        List<WebElement> product = driver.findElements(By.cssSelector("td.item"));
        System.out.println("Всего товаров в корзине " + product.size());

        for (int i = 0; i < product.size(); i++) {
            System.out.println("Удаляем " + (i + 1) + " товар");
            // Ждем появление кнопки Remove
            WebElement button = wait.until(visibilityOfElementLocated(By.name("remove_cart_item")));
            button.click(); // нажимаем на кнопку
            wait.until(stalenessOf(button)); // ждем исчезновение кнопки

            if (i + 1 == product.size()) { // если удалили последний товар
                // то ожидаем финальное сообщение
                (new WebDriverWait(driver, 5)).until(ExpectedConditions
                        .textToBePresentInElement(By.cssSelector("div#checkout-cart-wrapper p"),
                                "There are no items in your cart."));
                System.out.println("Корзина пуста. Финальное сообщение отобразилось.");

            } else { // если удалили непоследний товар (в корзине есть еще товары)
                // то ожидаем, пока удаленный товар исчезнет в итоговой таблицы
                wait.until(stalenessOf(product.get(i)));
                System.out.println("Товар " + (i + 1) + " отсутствует в корзине.");
            }
        }

    }
}
