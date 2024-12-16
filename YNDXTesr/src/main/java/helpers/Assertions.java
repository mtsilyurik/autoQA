package helpers;

import io.qameta.allure.Step;

/**
 * Класс переопределения ассертов
 * @author Михаил Цилюрик
 */
public class Assertions {

    @Step("Проверка, что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message){
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
}
