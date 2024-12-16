package helpers;

import org.aeonbits.owner.ConfigFactory;
/**
 * Класс подключения параметров
 * @author Михаил Цилюрик
 */
public class Properties {
    public static TestsProperties testsProperties = ConfigFactory.create(TestsProperties.class);
}
