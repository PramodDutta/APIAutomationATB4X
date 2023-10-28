package com.thetestingacademy.base;

import com.thetestingacademy.actions.AssertActions;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    //  Before Running a Method -
    //  Rest Assured . given with the Base URI. PATH
    public static RequestSpecification requestSpecification = RestAssured.given();
    public static AssertActions actions;
    public static PayloadManager payloadManager;
    public static JsonPath jsonPath;
    public static Response response;

    public static ValidatableResponse validatableResponse;


    @BeforeMethod
    public void setConfig() {
        // Reset the Rest Assured Base URLs
        // Base URL
        // Content Type - ALL

        System.out.println("I am able to run");
        payloadManager = new PayloadManager();
        actions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON);

//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL)
//                .addHeader("Content-Type", "application/json")
//                .build().log().all();

    }


}
