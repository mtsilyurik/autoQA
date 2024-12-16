package ru.yandexMarket;
import helpers.Assertions;
import helpers.Properties;
import io.qameta.allure.Feature;
//import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;
import pages.CatalogPage;
import pages.YandexMarketPage;

import java.util.List;

import static steps.Steps.*;
import static helpers.Assertions.*;
/**
 * Основной класс теста
 */
public class TestYandexMarket extends BaseTest{
    /**
     * URL-адрес Яндекс Маркета
     */
    private String yndxMarketUrl = Properties.testsProperties.yandexMarketUrl();

    /**
     * Заголов главной страницы Яндекс Маркета
     */
    private String yndxMarketTitle = "Интернет-магазин Яндекс Маркет — покупки с быстрой доставкой";


    @Feature("Тест каталога Яндекс Маркета")
    @DisplayName("Проверка Яндекс Маркета")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#yandexMarketTestData")
    public void testCase_1_4(String catalogSubTitle,
                             String category,
                             String[] priceFilter,
                             String[] vendorsFilter
    ){
        openSite(yndxMarketUrl, yndxMarketTitle, chromeDriver);

        YandexMarketPage yandexMarketPage = new YandexMarketPage(chromeDriver);

        yandexMarketPage.goToCatalog();

        yandexMarketPage.waitForCatalogSubtitles();

        moveMouseToElement(chromeDriver, yandexMarketPage.getCatalogSubTitle(catalogSubTitle));

        Assertions.assertTrue(yandexMarketPage.getCatalogSubHeader().equals(catalogSubTitle), "Не навел курсор на подраздел "+catalogSubTitle);

        yandexMarketPage.goByLinkInCatalog(category);

        CatalogPage catalogPage = new CatalogPage(chromeDriver);

        Assertions.assertTrue(chromeDriver.getTitle().contains(category), "Не перешел на страницу с "+category);

        catalogPage.setPriceFilter(priceFilter[0], priceFilter[1]);

        catalogPage.setVendors(vendorsFilter);

        scrollToTop(chromeDriver);

        List<WebElement> items = catalogPage.getResultItemsList();

        Assertions.assertTrue(items.size() > 12, "На странице меньше 12 элементов");

        boolean validate = catalogPage.validateItems(items, vendorsFilter, priceFilter[0], priceFilter[1]);
        Assertions.assertTrue(validate, "Товары не соответствуют выбранным фильтрам");

        String firstItemTitle = catalogPage.getItemTitle(0);

        findItemOnCatalogPage(catalogPage, firstItemTitle);

        List<String> titlesAfterSearch = catalogPage.getResultTitles();

        boolean isTitleOnPage = titlesAfterSearch.contains(firstItemTitle);
        Assertions.assertTrue(isTitleOnPage, "На странице нет товара "+firstItemTitle);
    }
}
