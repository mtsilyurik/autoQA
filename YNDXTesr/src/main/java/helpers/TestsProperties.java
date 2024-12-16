package helpers;

import org.aeonbits.owner.Config;
/**
 * Класс конфигурации параметров
 * @author Михаил Цилюрик
 */

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"
})
public interface TestsProperties extends Config{

    @Key("yandex.market.url")
    String yandexMarketUrl();

}
