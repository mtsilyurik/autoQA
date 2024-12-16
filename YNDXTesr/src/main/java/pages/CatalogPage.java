package pages;

import helpers.CustomWait;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Класс страницы каталога
 * @author Михаил Цилюрик
 */
public class CatalogPage {
    /**
     * Путь до кнопки "Найти" на странице каталога для поиска товара
     */
    private final String SEARCH_BUTTON_XPATH = "//button[@data-auto='search-button']";
    /**
     * Путь до поля поиска товара
     */
    private final String SEARCH_INPUT_XPATH = "//input[@placeholder='Найти товары']";
    /**
     * Путь до кнопки показа всех фильтров по производителю
     */
    private final String SHOW_ALL_VENDORS_LINK_XPATH =
            "//div[contains(@data-zone-data, 'Бренд')]//span[text()='Показать всё']";
    /**
     * Путь до поля ввода производителя в блоке фильтров
     */
    private final String VENDORS_SEARCH_INPUT_XPATH = "//div[contains(@data-zone-data, 'Бренд')]//input";
    /**
     * Путь до поля ввода минимальной цены
     */
    private final String MIN_PRICE_INPUT_XPATH =
            "//div[contains(@data-auto, 'filter-range')]//input[contains(@id, 'price') and contains(@id, 'min')]";
    /**
     * Путь до поля ввода максимальной цены
     */
    private final String MAX_PRICE_INPUT_XPATH =
            "//div[contains(@data-auto, 'filter-range')]//input[contains(@id, 'price') and contains(@id, 'max')]";

//    private final String ITEMS_LIST_BLOCK_XPATH = "//div[@data-auto='SerpList']/div";

    /**
     * Путь до блока с товарами в каталоге
     */
    private final String ITEMS_LIST_BLOCK_XPATH = "//article[@data-auto='searchOrganic']//div[@data-zone-name='productSnippet']";
    /**
     * Путь до названия товара
     */
    private final String ITEM_TITLE_XPATH = ".//a/span";
    /**
     * Путь до блока со стоимостью товара
     */
    private final String PRICE_BLOCK_ITEM_XPATH =
            ".//span[contains(@class, 'ds-value') and @aria-hidden='true']/span[1]";
    /**
     * Драйвер браузера
     */
    protected WebDriver chromeDriver;
    /**
     * Элемент поле ввода минимальной цены
     */
    protected WebElement minPriceInput;
    /**
     * Элемент поля ввода максимальной цены
     */
    protected WebElement maxPriceInput;
    /**
     * Элемент поле поиска по каталогу
     */
    protected WebElement searchInput;
    /**
     * Элемент кнопка "Найти" в каталоге
     */
    protected WebElement searchButton;
    /**
     * Список элементов-товаров на странице каталога
     */
    protected List<WebElement> itemsListBlock;

    protected WebDriverWait wait;

    public CatalogPage(WebDriver driver) {
        this.chromeDriver = driver;
        wait = new WebDriverWait(driver, 10);
        this.minPriceInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MIN_PRICE_INPUT_XPATH)));
        this.maxPriceInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MAX_PRICE_INPUT_XPATH)));
        this.searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_INPUT_XPATH)));
        this.searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_BUTTON_XPATH)));
    }

    @Step("Настройка фильтров цены")
    public void setPriceFilter(String min, String max) {
        minPriceInput.click();
        minPriceInput.sendKeys(min);
        maxPriceInput.click();
        maxPriceInput.sendKeys(max);
    }
    /**
     * Настраивает фильтр производителя через поле поиска производителя по названию
     * @param values массив названий брендов
     */
//    @Step("Настройка фильтров производителя")
//    public void setVendors(String[] values){
//        for(String v : values){
//            WebElement moreVendorsLink = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath(SHOW_ALL_VENDORS_LINK_XPATH))
//            );
//            System.out.println("Выбрал больше вендоров");
//
//            moreVendorsLink.click();
//            System.out.println("Кликнул больше вендоров");
//
//            WebElement searchVendorInput = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath(VENDORS_SEARCH_INPUT_XPATH))
//            );
//            System.out.println("Нашел поле ввода");
//
//            searchVendorInput.click();
//            System.out.println("Кликнул на поле ввода");
//
//            searchVendorInput.sendKeys(v);
//            System.out.println("Ввел название "+v);
//
//            String vendorXPath = "//div[contains(@data-zone-data, 'Бренд')]//span[text()='"+v+"']";
//            WebElement vendor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(vendorXPath)));
//            System.out.println("Нашел вендора");
//
//            vendor.click();
//            System.out.println("Клинкул на вендора");
//        }
//    }

    @Step("Настройка фильтров производителя")
    public void setVendors(String[] values){
        if(!(values.length ==0)){
            WebElement moreVendorsLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(SHOW_ALL_VENDORS_LINK_XPATH))
            );
//            System.out.println("Выбрал больше вендоров");

            moreVendorsLink.click();
//            System.out.println("Кликнул больше вендоров");

            for(String v : values){
                WebElement searchVendorInput = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath(VENDORS_SEARCH_INPUT_XPATH))
                );
//                System.out.println("Нашел поле ввода");

                searchVendorInput.click();
//                System.out.println("Кликнул на поле ввода");

                searchVendorInput.clear();
//                System.out.println("Очистил поле");

                searchVendorInput.sendKeys(v);
//                System.out.println("Ввел название "+v);

                String vendorXPath = "//div[contains(@data-zone-data, 'Бренд')]//span[text()='"+v+"']";
                WebElement vendor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(vendorXPath)));
//                System.out.println("Нашел вендора");

                vendor.click();
//                System.out.println("Клинкул на вендора");
            }
        }
    }

    /**
     * Инициализирует переменную itemsListBlock
     * @return список элементов-товаров
     */
//    public List<WebElement> getResultItemsList(){
//        this.itemsListBlock = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                By.xpath(ITEMS_LIST_BLOCK_XPATH))
//        );
//        return this.itemsListBlock;
//    }

    public List<WebElement> getResultItemsList(){
        this.itemsListBlock = CustomWait.waitForElementsToBeVisible(ITEMS_LIST_BLOCK_XPATH ,10, chromeDriver);
        return this.itemsListBlock;
    }


    /**
     * Проверяет соответсвие найденным товарам применненым фильтрам
     * @param items список найденных элементов-товаров
     * @param vendors массив названий производителей
     * @param minPrice минимальная цена товаров
     * @param maxPrice максимальная цена товаров
     */
    public boolean validateItems(List<WebElement> items, String[] vendors, String minPrice, String maxPrice){

        List<Boolean> valid = new ArrayList<>();

        for(WebElement item : items){
            boolean t = false;
            boolean p;
            String title = item.findElement(By.xpath(ITEM_TITLE_XPATH)).getText();
            String price = item.findElement(By.xpath(PRICE_BLOCK_ITEM_XPATH)).getText();
            List<String> pr = new ArrayList<>(List.of(price.split("")));
            pr.remove(2);
            price = String.join("", pr);
            for(String vendor : vendors){
                if(title.toLowerCase().contains(vendor.toLowerCase())){
                    t = true;
                    break;
                }
            }

            p = Integer.parseInt(price) >= Integer.parseInt(minPrice)
                    && Integer.parseInt(price) <= Integer.parseInt(maxPrice);

//            System.out.println(title);
//            System.out.println(price);
//            System.out.println();

            valid.add(p&&t);
        }


        return valid.stream().allMatch(v -> v.booleanValue());
    }

    /**
     * Возвращает название товара в списке найденных товаров по его индексу в выдаче на странице каталога
     * @param itemIndex индекс искомого товара
     * @return название выбранного товара
     */
    public String getItemTitle(int itemIndex){
        String title = this.itemsListBlock.get(itemIndex).findElement(By.xpath(ITEM_TITLE_XPATH)).getText();
        return title;
    }

    /**
     * @return список названий всех найденных товаров
     */
    public List<String> getResultTitles(){
        List<String> titles = new ArrayList<>();
        for(WebElement item : this.itemsListBlock){
            String title = item.findElement(By.xpath(ITEM_TITLE_XPATH)).getText();
            titles.add(title);
        }
        return titles;
    }

    /**
     * Ищет товар на странице каталога
     * @param query искомый товар
     */
    public void search(String query){
        searchInput.click();
        searchInput.sendKeys(query);
        searchButton.click();
        this.itemsListBlock = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(ITEMS_LIST_BLOCK_XPATH))
        );
    }
}
