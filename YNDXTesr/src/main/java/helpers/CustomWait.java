package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CustomWait {

    private static WebDriver driver;

    private static void sleep(int sec){
        try{
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<WebElement> waitForElementsToBeVisible(String xpath, int timeoutInSeconds, WebDriver driver) {
        sleep(timeoutInSeconds);
        return driver.findElements(By.xpath(xpath));
    }

    public static WebElement waitForElementToBeVisible(String xpath, int timeoutInSeconds, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                WebElement element = d.findElement(By.xpath(xpath));
                return (element.isDisplayed() && element.isEnabled()) ? element : null;
            }
        });
    }
}
