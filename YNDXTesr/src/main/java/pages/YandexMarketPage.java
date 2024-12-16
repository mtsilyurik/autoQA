package pages;

import helpers.Assertions;
import helpers.CustomWait;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Осовной класс теста
 *
 * @author Михаил Цилюрик
 */
public class YandexMarketPage {

    private final String CATALOG_SUBTITLES_XPATH = "//div[@data-zone-name='catalog-content']//ul[@role='tablist']";

    /**
     * Путь до кнопки каталога
     */
    private final String CATALOG_BUTTON_XPATH = "//div[@data-zone-name='catalog']/button";
    /**
     * Путь до заголовка раздела каталога
     */
    private final String CATALOG_SUBHEADER_XPATH = "//div[@role='heading']/a";

    /**
     * Драйвер браузера
     */
    protected WebDriver chromeDriver;

    protected WebDriverWait wait;
    /**
     * Элемент кнопка каталога
     */
    protected WebElement catalogButton;
    /**
     * Элемент заголовок раздела каталога
     */
    protected WebElement electronicsTitle;

    public YandexMarketPage(WebDriver driver) {
        this.chromeDriver = driver;
        this.wait = new WebDriverWait(chromeDriver, 10);
//        this.catalogButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CATALOG_BUTTON_XPATH)));
        this.catalogButton = CustomWait.waitForElementToBeVisible(CATALOG_BUTTON_XPATH, 5, chromeDriver);
    }


    @Step("Переход в каталог")
    public void goToCatalog(){
        catalogButton.click();
    }

    /**
     * @return название текущего выбранного подраздела каталога
     */
    public String getCatalogSubHeader(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CATALOG_SUBHEADER_XPATH))).getText();
//        return CustomWait.waitForElementToBeVisible(CATALOG_SUBHEADER_XPATH, 30, this.chromeDriver).getText();
    }

    @Step("Переход в раздел каталога: {target}")
    public void goByLinkInCatalog(String target){
        String xpath = "//ul[@data-autotest-id='subItems']//a[text()='"+target+"']";
        WebElement linkInCatalog = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        linkInCatalog.click();
    }

    /**
     * @return веб-элемент названия выбранного подраздела каталога
     * @param subTitle название подраздела каталога
     */
    public WebElement getCatalogSubTitle(String subTitle) {
        String xpath = "//div[@data-zone-name='catalog-content']//ul[@role='tablist']//span[text()='" + subTitle + "']";
//        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        WebElement el = CustomWait.waitForElementToBeVisible(xpath, 30, this.chromeDriver);
        return el;

    }

    public void waitForCatalogSubtitles(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CATALOG_SUBTITLES_XPATH)));
    }
}
