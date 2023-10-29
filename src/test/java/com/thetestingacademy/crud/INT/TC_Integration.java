package com.thetestingacademy.crud.INT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.payloads.request.Booking;
import com.thetestingacademy.payloads.response.BookingRespons;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assert;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.assertj.core.api.Assertions.*;
public class TC_Integration extends BaseTest {
    String token;
    String bookingId;
    String bookingIdPojo;

    // Create a Booking
    // Update the Booking with Token and Booking ID - How to pass the variales from one test to another.
        // 1. Auth - API Key
        // Cookie Based Auth Side.
        // OAuth 2.0 - Method how you can use the OAuth 2.0
    // Delete Also



    // Get a Token - Extract the Token

    private static final Logger log = LogManager.getLogger(TC_Integration.class);


    // Create a Booking
    @Test(groups = "P0")
    public void testCreateBooking() throws JsonProcessingException {
        token = getToken();
        assertThat(token).isNotNull().isNotEmpty();

        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(200);
        // Direct Extraction from json Path
        bookingId = jsonPath.getString("bookingid");
        // Booking Response Class
        BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
        bookingIdPojo = bookingRespons.getBookingid().toString();
        log.info("This is My Booking ID"+bookingIdPojo);
        assertThat(bookingId).isNotNull().isNotEmpty();

    }


    //Update the Booking with Token and Booking ID
    @Test(groups = "P0", dependsOnMethods = {"testCreateBooking"})
    public void testCreateAndUpdateBooking() throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().body(payloadManager.updatedPayload()).put();
        validatableResponse = response.then().log().all();
        //validatableResponse.body("firstname", Matchers.is("Lucky"));

        Booking bookingRespons = payloadManager.JsonToObjectPUT(response.asString());
        assertThat(bookingRespons.getFirstname()).isEqualTo("Lucky").isNotNull();
        assertThat(bookingRespons.getLastname()).isNotNull();
        assertThat(bookingRespons.getDepositpaid()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckin()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckout()).isNotEmpty();

    }

    // Delete Also
    @Test(groups = "P0",dependsOnMethods = { "testCreateAndUpdateBooking"})
    public void testDeleteCreatedBooking(){
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId).cookie("token",token);
        ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123")
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test(groups = "P0",dependsOnMethods = { "testDeleteCreatedBooking"})
    public void testDeleteBookingByGet(){
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING+"/"+bookingId);
        response = RestAssured.given().spec(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
    }


}
