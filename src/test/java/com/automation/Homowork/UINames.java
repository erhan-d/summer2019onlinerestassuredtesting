package com.automation.Homowork;

import com.automation.pojos.Spartan;
import com.automation.pojos.Student;
import com.github.javafaker.Faker;
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
        baseURI = ConfigurationReader.getProperty("uinames.uri");
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
    public void test() {

        List<String > ecpected=Arrays.asList("name", "surname", "gender", "region fields");

       Response response=  given()
                .accept(ContentType.JSON).
                when().
                get("");

           List<String >actual=response.jsonPath().getList("");
           assertEquals(ecpected,actual);




    }
}























