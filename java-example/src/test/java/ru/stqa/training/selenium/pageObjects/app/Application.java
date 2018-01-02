package ru.stqa.training.selenium.pageObjects.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import ru.stqa.training.selenium.pageObjects.pages.CartPage;
import ru.stqa.training.selenium.pageObjects.pages.MainStorePage;
import ru.stqa.training.selenium.pageObjects.pages.ProductPage;

import java.util.concurrent.TimeUnit;

public class Application {
    public WebDriver wd; // обявляем драйвер

    // объявляем делегатов
    private MainStorePage mainStorePage;
    private ProductPage productPage;
    private CartPage cartPage;

    private String browser;

    public Application(String browser) {
        this.browser = browser;
    }

    // инициализируем браузер
    public void init() {
        if (browser.equals(org.openqa.selenium.remote.BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else  if (browser.equals(org.openqa.selenium.remote.BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(org.openqa.selenium.remote.BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Инициализируем делегатов
        mainStorePage = new MainStorePage(wd);
        productPage = new ProductPage(wd);
        cartPage = new CartPage(wd);
    }

    // выход
    public void stop() {
        wd.quit();
    }

    // геттеры делегатов
    public MainStorePage getMainStorePage() {
        return mainStorePage;
    }
    public ProductPage getProductPage() {
        return productPage;
    }
    public CartPage getCartPage() {
        return cartPage;
    }
}
