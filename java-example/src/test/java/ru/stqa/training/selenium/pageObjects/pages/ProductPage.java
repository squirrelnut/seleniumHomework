package ru.stqa.training.selenium.pageObjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCartAndCheckQuantity(){
        // получаем текущее количество товаров в корзине (до добавления нового товара)
        int currentQuantity = getIntFromTextContent(By.cssSelector("div#cart .quantity"));

        // добавляем товар в корзину
        addToCart();

        // ждем пока обновится количество товаров в корзине
        (new WebDriverWait(driver, 5)).until(ExpectedConditions
                .textToBePresentInElement(By.cssSelector("div#cart .quantity"),
                        Integer.toString(currentQuantity + 1)));
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
            boolean result = driver.findElements(By.cssSelector(locator)).size() > 0;
            return result;
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

}
