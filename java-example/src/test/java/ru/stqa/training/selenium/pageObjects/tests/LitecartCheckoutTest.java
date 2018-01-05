package ru.stqa.training.selenium.pageObjects.tests;

import org.junit.Test;

public class LitecartCheckoutTest extends TestBase {
    @Test
    public void testLitecartCheckout() {
        // добавляем товары в корзину
        addProductsToCart(3);

        // открываем корзину
        app.getProductPage().openCart();

        // удаляем товары из корзины и проверяем итоговую таблицу
        app.getCartPage().removeProductAndCheckTotal();

    }

    public void addProductsToCart(int number) {
        for (int i = 0; i < number; i++) {
            // открываем главную страницу
            app.getMainStorePage().openMainPage();

            // кликаем на первый товар
            app.getMainStorePage().openFirstProduct();

            // добавляем в корзину и проверяем количество
            app.getProductPage().addProductToCartAndCheckQuantity();
        }
    }

}
