package ru.yandexMarket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Базовый класс теста
 * @author Михаил Цилюрик
 */
public class BaseTest {
    /**
     * Драйвер браузер
     */
    protected WebDriver chromeDriver;

    /**
     * Выполняется перед вызовом каждого теста.
     * Устанавливает путь до драйвера.
     * Инициплизирует WebDriver
     * Настраивает отображение браузера и время ожидания загрузки элементов.
     */
    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "/Users/mikhailtsilyurik/chromedriver-130/chromedriver");
//        System.setProperty("webdriver.chrome.driver",  System.getenv("CHROME_DRIVER"));

//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY,"none");
//
//        chromeDriver = new ChromeDriver(capabilities);
        chromeDriver = new ChromeDriver();
//        chromeDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
    }

    /**
     * Выполняется после каждого завершения теста.
     * Закрывает браузер.
     */
    @AfterEach
    public void after() {
        chromeDriver.quit();
    }
}
