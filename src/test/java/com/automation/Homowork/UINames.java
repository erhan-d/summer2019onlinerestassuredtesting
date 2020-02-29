package com.automation.Homowork;

import com.automation.pojos.Spartan;
import com.automation.pojos.Student;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;

import com.automation.pojos.Job;
import com.automation.pojos.Location;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.baseURI;


public class UINames {

    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("ui.names.uri");
    }

    /*
     * No params test
     * 1.Send a get request without providing any parameters
     * 2.Verify status code 200,
     * content type application/json; charset=utf-83.
     * Verify that name, surname, gender, region fields have value
     * */
    @Test
    @DisplayName("Verify that name, surname, gender, region fields have value")
    public void test1() {

        given()
                .accept(ContentType.JSON).

                when().get("").prettyPeek()
                .then().statusCode(200)
                .body("name", not(empty()), "surname", not(empty()), "region", not(empty()));

    }

    //  Gender test 1.Create a request by providing query parameter: gender,
    //  male or female2.Verify status code 200,
    //  content type application/json; charset=utf-83.
    //  Verify that value of gender field is same from step 1
    @Test
    @DisplayName("Verify that value of gender field is same from step 1")
    public void test2() {
        given().accept(ContentType.JSON)
                .queryParam("gender", "male")
                .when().get("")
                .then().statusCode(200).contentType(ContentType.JSON).
                body("gender", is("male")).log().all();

    }
//2 params test
// 1.Create a request by providing query parameters:
// a valid region and gender NOTE: Available region values are given in the documentation
// 2.Verify status code 200, content type application/json; charset=utf-8
// 3.Verify that value of gender field is same from step 1
// 4.Verify that value of region field is same from step 1

    @Test
    @DisplayName("Verify that value of region field is same from step 1")
    public void test3() {

        given().accept(ContentType.JSON).
                queryParam("gender", "female").
                queryParam("region", "Romania").

                when().get("").
                then().statusCode(200).contentType(ContentType.JSON)
                .body("gender", is("female"))
                .body("region", is("Romania")).log().all();
    }

    //Invalid gender test
    // 1.Create a request by providing query parameter: invalid gender
    // 2.Verify status code 400 and status line contains Bad Request
    // 3.Verify that value of error field is Invalid gender


    @Test
    @DisplayName("Verify status code 400 and status line contains Bad Request")
    public void test4() {

        given().accept(ContentType.JSON).
                queryParam("gender", "mal").
                when().get("").
                then().statusCode(400).
                statusLine(containsString("Bad Request")).
                body("error", is("Invalid gender")).log().all();
    }
//invalid region test
// 1.Create a request by providing query parameter: invalid region
// 2.Verify status code 400 and status line contains Bad Request
// 3.Verify that value of error field is Regionorlanguagenotfound

    @Test
    @DisplayName("Verify status code 400 and status line contains Bad Request")
    public void test5() {

        given().accept(ContentType.JSON).
                queryParam("region", "mal").
                when().get("").
                then().statusCode(400).
                statusLine(containsString("Bad Request")).
                body("error", is("Region or language not found")).log().all();
    }
// Amount and regions test
// 1.Create request by providing query parameters:
// a valid region and amount(must be bigger than 1)
// 2.Verify status code 200,
// content type application/json; charset=utf-83.
// Verify that all objects have different name+surname combination

    @Test
    @DisplayName("Verify that all objects have different name+surname combination")
    public void test6() {
        Response response =
                given().accept(ContentType.JSON).
                        queryParam("region", "Romania").
                        when().get("").prettyPeek();

        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());

        String region = response.jsonPath().get("region");
        System.out.println(region);
    }

    // 3 params test
    // 1.Create a request by providing query parameters:
    // a valid region, gender and amount(must be bigger than 1)
    // 2.Verify status code 200, content type application/json; charset=utf-83.
    // Verify that all objects the response have the same region and gender passed in step 1

    @Test
    @DisplayName("Verify that all objects the response have the same region and gender passed in step 1")
    public void test7() {
        Response response =
                given().accept(ContentType.JSON).
                        queryParam("region", "Romania").
                        queryParam("gender", "male").
                        when().get("").prettyPeek();
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());
        assertEquals("Romania", response.jsonPath().getString("region"));

        String Name = response.jsonPath().getString("region");
        System.out.println(Name);

    }

//    Amount count test
//    1.Create a request by providing query parameter: amount (must be bigger than 1)
//    2.Verify status code 200, content type application/json; charset=utf-8
//    3.Verify that number of objects returned in the response is same as the amount passed in step 1

    @Test
    @DisplayName("Verify that number of objects returned in the response is same as the amount passed in step 1")
    public void test8() {
        Response response =
                given().accept(ContentType.JSON).
                        queryParam("gender", "male").
                        queryParam("region", "").
                        when().get("").prettyPeek();
    }

}





















