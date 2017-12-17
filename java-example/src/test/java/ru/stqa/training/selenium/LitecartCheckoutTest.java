package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LitecartCheckoutTest extends TestBase {
    @Test
    public void testLitecartCheckout() {
        // добавляем 3 товара в корзину
        addProductsToCart(3);

        // открываем корзину
        driver.findElement(By.linkText("Checkout »")).click();

        // удаляем товары из корзины и проверяем итоговую таблицу
        removeProductAndCheckTotal();

    }

    public void addProductsToCart(int number) {
        for (int i = 0; i < number; i++) {
            // открываем главную страницу
            driver.get("http://localhost/litecart/en/");

            // кликаем на первый товар
            driver.findElement(By.cssSelector("div.image-wrapper img")).click();

            // получаем текущее количество товаров в корзине (до добавления нового товара)
            int currentQuantity = getIntFromTextContent(By.cssSelector("div#cart .quantity"));

            // добавляем товар в корзину
            addToCart();

            // ждем пока обновится количество товаров в корзине
            (new WebDriverWait(driver, 5)).until(ExpectedConditions
                    .textToBePresentInElement(By.cssSelector("div#cart .quantity"),
                            Integer.toString(currentQuantity + 1)));

        }
    }

    public void addToCart() {
        // проверяем наличие поля size
        boolean size = isElementPresent("select[name='options[Size]']");
        // если поле есть, то заполняем его
        if (size) {
            WebElement selectElement = driver.findElement(By.cssSelector("select[name='options[Size]'"));
            Select select = new Select(selectElement);
            select.selectByVisibleText("Small");
        }

        // нажимаем кнопку добавления товара в корзину
        driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
    }

    public boolean isElementPresent(String locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return driver.findElements(By.cssSelector(locator)).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    public int getIntFromTextContent(By locator) {
        // получаем текст из тега
        String text = driver.findElement(locator)
                .getAttribute("textContent");

        // преобразуем его в целое число
        return Integer.parseInt(text);
    }

    public void removeProductAndCheckTotal() {
        // получаем все продукты из итоговой таблицы
        List<WebElement> product = driver.findElements(By.cssSelector("td.item"));

        for (int i = 0; i < product.size(); i++) {
            // удаляем товар
            driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();

            if (i + 1 == product.size()) { // если удалили последний товар в корзине
                // то ожидаем финальное сообщение
                (new WebDriverWait(driver, 20)).until(ExpectedConditions
                        .textToBePresentInElement(By.cssSelector("div#checkout-cart-wrapper p"),
                                "There are no items in your cart."));

            } else { // если удалили непоследний товар (в корзине есть др. товары)
                // то ожидаем, пока удаленный товар исчезнет в итоговой таблицы
                (new WebDriverWait(driver, 10)).until(ExpectedConditions
                        .stalenessOf(product.get(i)));
            }
        }

    }

}
