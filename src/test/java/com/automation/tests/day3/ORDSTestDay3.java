package com.automation.tests.day3;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestDay3 {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

    @Test
    public void test1() {
        given().
                accept("application/json").
                get("/employees").
                then().assertThat().statusCode(200).
                and().assertThat().contentType("application/json").
                log().all(true);
    }

    @Test
    public void test2() {
        given().
                accept("application/json").
                pathParam("id", 100).
                when().get("/employees/{id}").
                then().assertThat().statusCode(200).
                and().assertThat().body("employee_id", is(100),
                "department_id", is(90),
                "last_name", is("King")).
                log().all(true);
        //body ("phone_number") --> 515.123.4567
        //is is coming from ---> import static org.hamcrest.Matchers.*;
        //log().all  Logs everything in the response, including e.g. headers,
        // cookies, body with the option to pretty-print the body if the content-type is
    }

    /**
     * given path parameter is "/regions/{id}"
     * when user makes get request
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */
    @Test
    public void test() {
        given().
                accept("application/json").
                pathParam("id", 1).
                when().get("/regions/{id}").
                then().assertThat().statusCode(200).
                and().assertThat().body("region_name", is("Europe")).
                time(lessThan(10L), TimeUnit.SECONDS).
                log().body(true);// log body in pretty format.

    }
    @Test
    public void test4() {
       Response response = given().accept("application/json").
                        when().get("/employees");

        String nameOfFirstEmployee = response.jsonPath().getString("items[0].first_name");
        String nameofLastEmployee=response.jsonPath().getString("items[-1].first_name");

        System.out.println("First employee name: " + nameOfFirstEmployee);
        System.out.println("Last employee name: " + nameofLastEmployee);
// since firstEmployee it's a map(key-value pair,we can iterate through it by using Entry.
// entry represent one-key=value pair
        Map<String,?> firstEmployee=response.jsonPath().get("items[0]");
        System.out.println(firstEmployee);
        for(Map.Entry entry:firstEmployee.entrySet()){
            System.out.println("key: "+entry.getKey()+", value: "+entry.getValue());
        }
        List <String> lastNames=response.jsonPath().get("items.last_name");

        for(String str:lastNames)
        System.out.println("Last name: "+str);

    }
    //write a code to
    //get info from /countries as List<Map<String,?>>
    @Test
    public void test5() {
        JsonPath json = given().
                accept("application/json").
                when().
                get("/countries").prettyPeek().jsonPath();

        //get and print all last names
        List<HashMap<String,?>> allCountries = json.get("items");
        System.out.println(allCountries);

        for(HashMap<String,?> map:allCountries){
            System.out.println(map);
        }
    }
    //get collection of employee's salaries
    //then sort it
    @Test
    public void test6(){
        List<Integer> salaries = given().
                accept("application/json").
                when().get("/employees").
                thenReturn().jsonPath().get("items.salary");

        Collections.sort(salaries);
        Collections.reverse(salaries);
        System.out.println(salaries);
    }
    // get collection of phone numbers, from employees
    // and replace all dots "." in evry phone number with dash "-"
    @Test
    public void test7(){
        List<String> phoneNum = given().
                accept("application/json")
                .when().get("/employees")
                .thenReturn().jsonPath().get("items.phone_number");
        phoneNum.replaceAll(p->p.replace(".", "-"));

        System.out.println(phoneNum);
    }
    /** ####TASK#####
     *  Given accept type as JSON
     *  And path parameter is id with value 1700
     *  When user sends get request to /locations
     *  Then user verifies that status code is 200
     *  And user verifies following json path information:
     *      |location_id|postal_code|city   |state_province|
     *      |1700       |98199      |Seattle|Washington    |
     */
    @Test
 public void test8(){
     Response response=given().
             accept(ContentType.JSON).
             pathParam("id", 1700).
             when().get("/locations/{id}");
      response.
              then().assertThat().statusCode(200).
             assertThat().body("location_id", is(1700),
                        "postal_code",is("98199"),
                             "city",is("Seattle"),
                             "state_province",is("Washington")).
             log().body(true);
 }
 }










