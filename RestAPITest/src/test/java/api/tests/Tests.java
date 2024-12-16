package api.tests;


import data.LoginDto;
import data.LoginWithoutPasswordDto;
import data.ResponseResourceDto;
import data.ResponseUsersDto;
import helpers.TestData;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static specification.Specification.*;

public class Tests {

    @Test(description = "TestCase 2.1", dataProvider = "provider_2.1", dataProviderClass = TestData.class)
    public void testCase_2_1(int page, String expectedFileType){
        installSpec(responseSpec200(), requestSpecReqres());

        String url = "/api/users?page=" + page;

        ResponseUsersDto response = given()
                .when()
                .get(url)
                .then()
                .extract().body().as(ResponseUsersDto.class);

        Set<String> avatarLinks = new HashSet<>();
        response.getData().forEach(u -> {
            int i = u.getAvatar().lastIndexOf("/");
            Assert.assertTrue(u.getAvatar().substring(i).contains(expectedFileType),
                    "Не ссылка на файл изображение – " + u.getAvatar());
            avatarLinks.add(u.getAvatar().substring(i+1));
        });

        Assert.assertEquals(response.getData().size(), avatarLinks.size(), "Имена файлов повторяются");

        deleteSpec();
    }

    @Test(description = "TestCase 2.2 1", dataProvider = "provider_2.2 1", dataProviderClass = TestData.class)
    public void testCase_2_2_1(String email, String password){
        installSpec(responseSpec200(), requestSpecReqres());

        LoginDto loginData = new LoginDto(email, password);

        Response response = given()
                .body(loginData)
                .when()
                .post("/api/login")
                .then()
                .extract().response();

        JsonPath jsonResponse = response.jsonPath();
        Assert.assertNotNull(jsonResponse.get("token"), "Авторизация не пройдена: " + jsonResponse.prettyPrint());

        deleteSpec();
    }

    @Test(description = "TestCase 2.2 2", dataProvider = "provider_2.2 2", dataProviderClass = TestData.class)
    public void testCase_2_2_2(String username, String email){
        installSpec(responseSpec400(), requestSpecReqres());

        LoginWithoutPasswordDto loginData = new LoginWithoutPasswordDto(username, email);

        Response response = given()
                .body(loginData)
                .when()
                .post("/api/login")
                .then()
                .extract().response();

        JsonPath jsonResponse = response.jsonPath();
        Assert.assertNotNull(jsonResponse.get("error"),
                "Отствует сообщение об ошибке: "  + jsonResponse.prettyPrint());

        deleteSpec();
    }

    @Test(description = "TestCase 2.3")
    public void testCase_2_3(){
        installSpec(responseSpec200(), requestSpecReqres());

        ResponseResourceDto response = given()
                .when()
                .get("/api/resource")
                .then()
                .extract().body().as(ResponseResourceDto.class);

        List<Integer> years = new ArrayList<>();
        response.getData().forEach(r -> years.add(r.getYear()));

        List<Integer> sorted = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(years, sorted, "Не отсортированы по годам");

        deleteSpec();
    }

    @Test(description = "TestCase 2.4")
    public void testCase_2_4(){
        installSpec(responseSpec200(), requestSpecGateway());

        Response response = given()
                .when()
                .get("/")
                .then()
                .extract().response();

        XmlPath xml = response.xmlPath();
        String text = xml.prettyPrint();
        Assert.assertEquals(text.split("</").length, 15, "Не 15 тегов – " + text.split("</").length);
        deleteSpec();
    }
}
