package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LitecartCountriesTest extends TestBase {
    @Test
    public void testLitecartCountries(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.linkText("Countries")).click();

        //---------------------------------1а----------------------------
        // получаем все ссылки из таблицы Countries
        List<WebElement> countriesLinks = driver.findElements(By.cssSelector(".dataTable td:nth-child(5) a"));

        // создаем массив для записи названий стран
        ArrayList<String> countriesName = new ArrayList<>();

        // пробегаем по всем ссылкам и записываем в массив названия стран (тест между тегом "а")
        getAttributeFromArray(countriesLinks, countriesName,"textContent");

        // проверяем располагаются ли страны в алфавитном порядке
        String result = isAlphabeticalOrder(countriesName);
        // выводим на консоль результат
        System.out.println("На странице Countries страны расположены " + result);


        //---------------------------------1б----------------------------
        // получаем все 6-ые столбцы (Zones) в таблице Countries
        List<WebElement> countriesZones = driver.findElements(By.cssSelector("tr.row td:nth-child(6)"));

        // создаем массив для записи зон
        ArrayList<String> linksZones = new ArrayList<>();

        // пробегаем по всем зонам и записываем в массив linksZones только те ссылки, у которых зона !=0
        saveCountryLinkWhichHasZones(countriesZones,countriesLinks,linksZones);

        // Пробегаем по ссылкам из массива linksZones и проверяем, что на каждой странице все зоны по алфавиту
        zoneCheck(linksZones, ".dataTable td:nth-child(3) input");

        //--------------------------------- 2 ----------------------------
        // открыли страницу
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        // получаем все теги ссылок
        List<WebElement> geoZones = driver.findElements(By.cssSelector("tr.row td:nth-child(3) a"));

        // создаем массив для ссылок страниц
        ArrayList<String> geoZonesLinks = new ArrayList<>();

        // записываем в массив geoZonesLinks адреса (свойство href)
        getAttributeFromArray(geoZones, geoZonesLinks, "href");

        // Пробегаем по всем ссылкам массива geoZonesLinks и проверяем, что на каждой странице зоны по алфавиту
        zoneCheck(geoZonesLinks, ".dataTable td:nth-child(3) [selected]");
    }

    public void getAttributeFromArray(List<WebElement> webElementsArray, ArrayList attributeArray, String attribute) {
        for (int i = 0; i < webElementsArray.size(); i++) {
            String a = webElementsArray.get(i).getAttribute(attribute);
            attributeArray.add(a);
        }
    }

    public String isAlphabeticalOrder(ArrayList<String> arrayNotSorted) {
        // создаем дубликат массива названий стран и отсортировываем его
        ArrayList<String> arraySorted = arrayNotSorted;
        Collections.sort(arraySorted);

        //поэлементно сравниваем 2 массива (сортированный и несортированный) и выводим результат в консоль
        if (arraySorted.equals(arrayNotSorted)) {
            return "в алфавитном порядке.";
        } else {
            return "не в алфавитном порядке.";
        }
    }

    public void saveCountryLinkWhichHasZones(List<WebElement> countriesZones,
                                             List<WebElement> countriesLinks,
                                             ArrayList<String> linksZones){
        for (int i = 0; i < countriesZones.size(); i++) {
            String a = countriesZones.get(i).getAttribute("textContent");
            int number = Integer.parseInt(a);
            if (number != 0) {
                String link = countriesLinks.get(i).getAttribute("href");
                linksZones.add(link);
                //System.out.println(link);
            }
        }
    }

    public void zoneCheck(ArrayList<String> linksZones, String locator) {
        for (int i=0; i<linksZones.size(); i++){
            driver.get(linksZones.get(i));
            List<WebElement> zonesName = driver.findElements(By.cssSelector(locator));
            ArrayList<String> zonesNameTextContent = new ArrayList<>(); // создали новый массив
            getAttributeFromArray(zonesName,zonesNameTextContent,"textContent"); // заполнили массив названиями зон
            String resultZone = isAlphabeticalOrder(zonesNameTextContent); // проверяем массив на алфавитный порядок
            System.out.println("На странице " + linksZones.get(i) + " зоны расположены " + resultZone);
        }
    }
}
