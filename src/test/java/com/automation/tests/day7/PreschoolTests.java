package com.automation.tests.day7;
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


public class PreschoolTests {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("school.uri");
    }

    //De-serialization Json to pojo class
    @Test
    @DisplayName("Get student with id 2633 and convert payload into POJO")
    public void test1(){
        Response response = given().
                accept(ContentType.JSON).
                pathParam("id", 2633).
                when().
                get("/student/{id}").prettyPeek();
        Student student = response.jsonPath().getObject("students[0]", Student.class);
        System.out.println(student);
        assertEquals(2633, student.getStudentId());
        assertEquals(11, student.getBatch());
        assertEquals("123456" ,student.getAdmissionNo());
        assertEquals("7925 Jones Branch Dr #3300", student.getContact().getPermanentAddress());
    }

    @Test
    @DisplayName("Get student with id 3024 and convert payload into POJO")
    public void test2(){

  Response response= given().
          accept(ContentType.JSON).
          pathParam("id", 3024).
          when().get("/student/{id}").prettyPeek();

  Student student=response.jsonPath().getObject("students[0]",Student.class);
        System.out.println(student);

    }

    @Test
    @DisplayName("Get student with id 2761 and convert payload into pojo")
    public void test3(){
       Response response=
                 given().accept(ContentType.JSON)
                .pathParam("id",2761)
                .when().get("/student/{id}").prettyPeek();

        Student student=response.jsonPath().getObject("students[0]",Student.class);
        System.out.println(student);
    }


}
