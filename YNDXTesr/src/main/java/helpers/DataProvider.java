package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
/**
 * Класс параметризации теста
 * @author Михаил Цилюрик
 */
public class DataProvider {

    public static Stream<Arguments> yandexMarketTestData(){
        return Stream.of(
                Arguments.of("Электроника", "Ноутбуки", new String[]{"10000", "30000"}, new String[]{"HP", "Lenovo"})
        );
    }
}
