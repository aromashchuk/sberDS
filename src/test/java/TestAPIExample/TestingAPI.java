package TestAPIExample;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestingAPI {

    @Test (priority = 1)
    void TestingGet() {

        given().
                get("https://reqres.in/api/users?page=2").
        then().
                statusCode(200).
                body("data.id[1]", equalTo(8)).
                body("data.first_name", hasItems("Michael", "Lindsay"));
    }

    @Test (priority = 2)
    public void TestingPostRequest(){
        JSONObject request = new JSONObject();

        request.put("name", "Artur");
        request.put("job", "QA Engineer");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("https://reqres.in/api/users").
        then().
                statusCode(201);
    }

    @Test (priority = 3)
    public void TestPutExample(){
        JSONObject request = new JSONObject();

        request.put("name", "Artur");
        request.put("job", "QA Automation Engineer");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                put("https://reqres.in/api/users/2").
        then().
                statusCode(200).
                log().all();
    }

    @Test (priority = 4)
    public void TestingDeleteExample(){

        when().
                delete("https://reqres.in/api/users/2").
        then().
                statusCode(204).
                log().all();
    }
}
