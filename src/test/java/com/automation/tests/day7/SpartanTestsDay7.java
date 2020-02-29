package com.automation.tests.day7;

import com.automation.pojos.Spartan;
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

public class SpartanTestsDay7 {
    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("spartan.uri");
    }

    //Serialization pojo to json
    @Test
    @DisplayName("Add new user by using extarnal JSON file ")
    public void test() {
        File file = new File(System.getProperty("user.dir") + "/spartan.json");

        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(file)
                .when()
                .post("/spartans").prettyPeek()
                .then().assertThat().statusCode(201)
                .body("success", is("A Spartan is Born!"));
    }

    @Test
    @DisplayName("Add new user by using extarnal JSON file ")
    public void test1_b() {

        File file= new File(System.getProperty("user.dir")+"/spartan.json");

        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(file)
                .when()
                .post("/spartans").prettyPeek()
                .then().assertThat().statusCode(201)
                .body("success",is("A Spartan is Born!") );

    }

    @Test
    @DisplayName("Add new user by using map ")
    public void test2_a() {

        Map<String, Object> spartan=new HashMap <>();

                spartan.put("name","Holy");
                spartan.put("gender","Male");
                spartan.put("phone",4545577568L);
         given().contentType(ContentType.JSON)
                 .accept(ContentType.JSON)
                 .body(spartan).
         when().post("/spartans").prettyPeek().
                 then().assertThat().statusCode(201).
                 body("success",is("A Spartan is Born!"));
    }

    @Test
    @DisplayName("Add new user by using map ")
    public void test2() {

        Map <Object, Object> spartan = new HashMap <>();
        spartan.put("phone", 2546466667L);
        spartan.put("gender", "Male");
        spartan.put("name", "Holy Deer");
        //u must specify content type, whenever u post
        // contentType(ContentType.JSON)
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/spartans").prettyPeek()
                .then().assertThat()
                .assertThat().statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Holy Deer"))
                .body("data.gender", is("Male"));
    }

    @Test
    @DisplayName("update spartan, only name Patch ")
    public void test3() {
        Map <Object, Object> update = new HashMap <>();
        update.put("name","HolyKhursad");
        update.put("gender","Female");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(update)
                .pathParam("id",604)
                .when().patch("/spartans/{id}").prettyPeek()
                .then().assertThat()
                .statusCode(204);


    }





}
