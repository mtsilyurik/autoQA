package specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    public static ResponseSpecification responseSpec200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpec400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static RequestSpecification requestSpecReqres(){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setContentType("application/json")
                .build();
    }

    public static RequestSpecification requestSpecGateway(){
        return new RequestSpecBuilder()
                .setBaseUri("https://gateway.autodns.com/")
                .setContentType("text/xml")
                .build();
    }

    public static void installSpec(ResponseSpecification spec){
        RestAssured.responseSpecification = spec;
    }

    public static void installSpec(RequestSpecification spec){
        RestAssured.requestSpecification = spec;
    }

    public static void installSpec(ResponseSpecification responseSpec, RequestSpecification requestSpec){
        RestAssured.responseSpecification = responseSpec;
        RestAssured.requestSpecification = requestSpec;
    }

    public static void deleteSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.requestSpecification = null;
    }
}
