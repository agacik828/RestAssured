package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public class ProjectCreationTest {

    @Test
    public void userCanCreateAProjectTest(){

        RestAssured.baseURI = "https://api.todoist.com";
        RestAssured.basePath = "/rest/v1/";
        RestAssured.requestSpecification = RestAssured.given().header("Authorization","Bearer 42eae56515288e63f29d04c8c643559e3432028e")
                .contentType(ContentType.JSON);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String projectName = "FirstTest";

        RestAssured.given()
                .body(format("{\"name\": \"%s\"}", projectName))
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name",equalTo(projectName))
                .header("Content-Type",equalTo("application/json"));
    }
}
