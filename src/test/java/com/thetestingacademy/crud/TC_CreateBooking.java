package com.thetestingacademy.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thetestingacademy.actions.AssertActions;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayloadManager;
import com.thetestingacademy.payloads.response.BookingRespons;
import com.thetestingacademy.utils.YAMLReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TC_CreateBooking extends BaseTest {


    @Test(groups = {"stage","P0"})
    @Owner("Pramod")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC#1 - Verify that Create Booking works and ID is generated!")
    public void testCreateBooking() throws JsonProcessingException {
        // Call the Request Block
        // Call the Payload
        // Call the Assertion Block
        payloadManager = new PayloadManager();
        actions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = requestSpecification.when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
        assertThat(bookingRespons.getBookingid().toString()).isNotEmpty().isNotNull();
    }

    @Test(groups = {"stage","P0"})
    @Description("TC#2 -  Verify that Create Booking with No payload")
    public void testCreateBooking2_Negative() throws JsonProcessingException {
        payloadManager = new PayloadManager();
        actions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = requestSpecification.when().body("").post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);
        //new YAMLReader().readKey().get("username");
        //new ProeprReader().readKey().get("username");
        //new ExcelReader().readKey().get("username");
        //new JSONReader().readKey().get("username");
        //new TETXReader().readKey().get("username");
        //new ENVReader().readKey().get("username");
        //new XMLReader.readKey().get("username");
    }

}
