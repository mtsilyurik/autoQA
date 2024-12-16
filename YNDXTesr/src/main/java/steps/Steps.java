package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CatalogPage;
import pages.YandexMarketPage;
/**
 * Шаги теста
 * @author Михаил Цилюрик
 */
public class Steps {

    private static WebDriverWait wait;
    private static WebDriver chromeDriver;

    @Step("Переход на станицу {url}")
    public static void openSite(String url, String title, WebDriver driver){
        chromeDriver = driver;
        chromeDriver.get(url);
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.titleIs(title));
    }


    @Step("Наведение курсора на элемент")
    public static void moveMouseToElement(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    @Step("Поиск товара {item}")
    public static void findItemOnCatalogPage(CatalogPage page, String item){
        page.search(item);
    }
    @Step("Поднимаемся в начало страницы")
    public static void scrollToTop(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

}
