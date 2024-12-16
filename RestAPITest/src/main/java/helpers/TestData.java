package helpers;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "provider_2.1")
    public Object[][] provider_2_1(){
        return new Object[][]{
                {2, ".jpg"},
                {1, ".jpg"}
        };
    }

    @DataProvider(name = "provider_2.2 1")
    public Object[][] provider_2_2_1(){
        return new Object[][]{
//                {"Username", "username@user.ru", "Password"},
//                {"AnotherUsername", "another@user.ru", "AnotherPassword"},
                {"eve.holt@reqres.in", "cityslicka"}
        };
    }

    @DataProvider(name = "provider_2.2 2")
    public Object[][] provider_2_2_2(){
        return new Object[][]{
                {"Username", "username@user.ru"},
                {"AnotherUsername", "another@user.ru"},
        };
    }
}
