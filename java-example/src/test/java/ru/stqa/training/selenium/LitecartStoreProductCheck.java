package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class LitecartStoreProductCheck extends TestBase {
    public class ProductData {
        public String name;
        public String regularPrice;
        public String campaignPrice;
        boolean isCrossedRegularPrice;
        boolean isGrayColorRegularPrice;
        boolean isBoldСampaignPrice;
        boolean isRedColorСampaignPrice;
        boolean isTextSizeСampaignPriceBigger;
    }

    @Test
    public void testLitecartStoreProductCheck() {
        // создаем 2 экземпляра класса ProductData
        ProductData dataFromMainPage = new ProductData();
        ProductData dataFromProductPage = new ProductData();

        // главная страница
        driver.get("http://localhost/litecart/en/");
        // Получаем все свойства на главной странице и записываем в объект dataFromMainPage
        dataFromMainPage = makeProductFormCurrentPage(
                "#box-campaigns div.name",
                "#box-campaigns div.price-wrapper .regular-price",
                "#box-campaigns div.price-wrapper .campaign-price");

        // переход на страницу товара
        driver.findElement(By.cssSelector("#box-campaigns img")).click();
        // Получаем все свойства на странице товара и записываем в объект dataFromProductPage
        dataFromProductPage = makeProductFormCurrentPage(
                "#box-product h1",
                ".information .regular-price",
                ".information .campaign-price");


        // сравниваем объекты, полученные на 2-х страницах и выводим результат на консоль
        compareProductData(dataFromMainPage, dataFromProductPage);
    }

    public int[] getRGB(String color) {

        // Создаем массив для хранения значений цветов r,g,b
        int[] rgb = new int[3];

        // узнаем индексы вхождения знаков открывающ. и закрывающ. скобок
        int bracketOpen = color.indexOf('(');
        int bracketClose = color.indexOf(')');

        // обрезаем все что вне скобок
        color = color.substring(bracketOpen + 1, bracketClose);

        // узнаем индекс первой запятой
        int comma1 = color.indexOf(',');
        // обрезаем значение цвета r и сохраняем в переменную
        String element = color.substring(0, comma1);
        rgb[0] = Integer.parseInt(element); // конвертируем в целое число и записываем в массив
        // оставляем в переменной color только значения g и b (+ альфа если есть)
        color = color.substring(comma1 + 2, color.length());


        // узаем индекс второй запятой
        int comma2 = color.indexOf(',');
        // обрезаем значение цвета g и сохраняем в переменную
        element = color.substring(0, comma2);
        rgb[1] = Integer.parseInt(element);
        // оставляем в color только значение b (+ альфа если есть)
        color = color.substring(comma2 + 2, color.length());

        // узаем индекс третьей запятой
        int comma3 = color.indexOf(',');
        if (comma3 != -1) { // если третья запятая есть, то
            element = color.substring(0, comma3); // вырезаем все что до нее
            rgb[2] = Integer.parseInt(element);
        } else { // если третьей запятой нет, то
            element = color; // то забираем все что осталось от цвета, это и есть значение b
            rgb[2] = Integer.parseInt(element);
        }

        //System.out.println("r = " + rgb[0] + " g = " + rgb[1] + " b = " + rgb[2]);
        return rgb;
    }

    public double getTextSize(String textSize) {
        // удаляем из строки последние 2 символа ("px")
        String cut = textSize.substring(0, textSize.length() - 2);
        double size = Double.parseDouble(cut); // конвертируем в double
        return size;
    }

    public ProductData makeProductFormCurrentPage(String nameSelector,
                                                  String regularPriceSelector,
                                                  String campaignPriceSelector) {

        // создаем пустой экземпляр объекта ProductData
        ProductData productData = new ProductData();

        // присваиваем ему строковые свойства
        productData.name = driver.findElement(By.cssSelector(nameSelector))
                .getAttribute("textContent");
        productData.regularPrice = driver.findElement(By.cssSelector(regularPriceSelector))
                .getAttribute("textContent");
        productData.campaignPrice = driver.findElement(By.cssSelector(campaignPriceSelector))
                .getAttribute("textContent");


        // присваиваем ему булевые свойства
        // проверяем зачеркнута ли обычная цена
        String textDecorationLineRP = driver.findElement(By.cssSelector(regularPriceSelector))
                .getCssValue("text-decoration-line");
        if (textDecorationLineRP.equals("line-through")) {
            productData.isCrossedRegularPrice = true;
        } else {
            productData.isCrossedRegularPrice = false;
        }

        // проверяем серый цвет обычной цены
        String textColorRegularPrice = driver.findElement(By.cssSelector(regularPriceSelector))
                .getCssValue("color");
        int[] textColorRP = getRGB(textColorRegularPrice); // получаем из строки цвета значения каналов rgb
        if (textColorRP[0] == textColorRP[1] && textColorRP[0] == textColorRP[2]) { // и сравниваем их
            productData.isGrayColorRegularPrice = true; // если все одинаковые, то цвет серый
        } else {
            productData.isGrayColorRegularPrice = false; // если разные, то цвет не серый
        }


        // проверяем полужирный стиль акционной цены
        String textDecorationСP = driver.findElement(By.cssSelector(campaignPriceSelector))
                .getCssValue("font-weight");
        int textDecoration = Integer.parseInt(textDecorationСP); // конвертируем значение в число
        if (textDecoration >= 700) { // если больше или равно 700
            productData.isBoldСampaignPrice = true; // то это полужирный шрифт
        } else {
            productData.isBoldСampaignPrice = false; //  то это не полужирный шрифт
        }


        // проверяем красный цвет акционной цены
        String textColorСampaignPrice = driver.findElement(By.cssSelector(campaignPriceSelector))
                .getCssValue("color");
        int[] textColorCP = getRGB(textColorСampaignPrice); // получаем из строки цвета значения каналов rgb
        if (textColorCP[1] == 0 && textColorCP[2] == 0) { // и сравниваем их. Если каналы G и B равны 0,
            productData.isRedColorСampaignPrice = true; //  то цвет красный
        } else {
            productData.isRedColorСampaignPrice = false; // если нет, то цвет не красный
        }

        // проверяем, что шрифт акционной цены больше чем шрифт обычной цены
        String textSizeRegularPrice = driver.findElement(By.cssSelector(regularPriceSelector))
                .getCssValue("font-size");
        String textSizeСampaignPrice = driver.findElement(By.cssSelector(campaignPriceSelector))
                .getCssValue("font-size");

        double textSizeRP = getTextSize(textSizeRegularPrice);
        double textSizeCP = getTextSize(textSizeСampaignPrice);
        if (textSizeCP > textSizeRP) { // если шрифт акционной цены больше обычной
            productData.isTextSizeСampaignPriceBigger = true; // то это свойство истино
        } else {
            productData.isTextSizeСampaignPriceBigger = false; // если нет, то ложно
        }

        return productData;
    }


    public void printProductData(ProductData productData) {
        System.out.println("Название товара = " + productData.name);
        System.out.println("Обычная цена = " + productData.regularPrice);
        System.out.println("Акционная цена = " + productData.campaignPrice);
        System.out.println("Обычная цена зачеркнута = " + productData.isCrossedRegularPrice);
        System.out.println("Обычная цена серго цвета = " + productData.isGrayColorRegularPrice);
        System.out.println("Акционная цена полужирная = " + productData.isBoldСampaignPrice);
        System.out.println("Акционная цена красного цвета = " + productData.isRedColorСampaignPrice);
        System.out.println("Шрифт акционной цены крупнее обычной = " + productData.isTextSizeСampaignPriceBigger);
    }

    public void compareProductData(ProductData mainPage, ProductData productPage) {
        // Выводим результат сравнения на консоль
        System.out.println("На главной странице и на странице товара:");
        System.out.println(compareStringData(mainPage.name, productPage.name)
                + " название товара");
        System.out.println(compareStringData(mainPage.regularPrice, productPage.regularPrice)
                + " обычная цена");
        System.out.println(compareStringData(mainPage.campaignPrice, productPage.campaignPrice)
                + " акционная цена");
        System.out.println(compareBooleanData(mainPage.isCrossedRegularPrice, productPage.isCrossedRegularPrice)
                + " стиль обычной цены - зачеркнутый");
        System.out.println(compareBooleanData(mainPage.isGrayColorRegularPrice, productPage.isGrayColorRegularPrice)
                + " цвет текста обычной цены - серый");
        System.out.println(compareBooleanData(mainPage.isBoldСampaignPrice, productPage.isBoldСampaignPrice)
                + " стиль акционной цены - полужирный");
        System.out.println(compareBooleanData(mainPage.isRedColorСampaignPrice, productPage.isRedColorСampaignPrice)
                + " цвет текста акционной цены - красный");
        System.out.println(compareBooleanData(mainPage.isTextSizeСampaignPriceBigger, productPage.isTextSizeСampaignPriceBigger)
                + " шрифт текста цен: акционная цена крупнее, чем обычная");
    }

    public String compareStringData(String mainPage, String productPage) {
        if (mainPage.equals(productPage)) {
            return "совпадает";
        } else {
            return "не совпадает";
        }
    }

    public String compareBooleanData(boolean mainPage, boolean productPage) {
        if (mainPage == productPage) {
            return "совпадает";
        } else {
            return "не совпадает";
        }
    }
}
