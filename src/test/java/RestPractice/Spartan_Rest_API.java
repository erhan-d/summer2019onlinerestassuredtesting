package RestPractice;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class Spartan_Rest_API {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.base_uri");
        RestAssured.port = Integer.parseInt(ConfigurationReader.getProperty("spartan.port"));
        RestAssured.basePath = ConfigurationReader.getProperty("spartan.basePath");
    }

    @Test
    public void test1() {

        given().accept(ContentType.JSON).
                when().get("spartans").prettyPeek().
                then().statusCode(200).
                assertThat().
                body("[0].name", is("Holy Moly")).
                body("[1].gender", is("Male")).
                body("name", hasItems("Holy Moly")).
                header("Transfer-Encoding", "chunked").
                header("Date", notNullValue())
        ;


    }

    @Test
    public void test2() {

        given().accept(ContentType.JSON).
                pathParam("my_id", 3).

                when().get("spartans/{my_id}").prettyPeek().
                then().statusCode(200).
                log().ifValidationFails();
    }
       @Test
    public void Add_NewSpartan_WithPojoAsBody(){

        Spartan spartan=new Spartan("MOly","Male","4345656406");

        given().log().all().
                contentType(ContentType.JSON).
                body(spartan).
                when().post("/spartans").
                then().log().all().
                statusCode(201).
                log().all().
                body("success",is("A Spartan is Born!"));
    }

    @Test
    public void Add_NewSpartan_WithNegetiveTest(){

        Spartan spartan=new Spartan("H","Male","4345656406");

        given().log().all().
                contentType(ContentType.JSON).
                body(spartan).
                when().post("/spartans").
                then().log().all().
                statusCode(400).
                log().all().
                body("error",is("Bad Request"));

    }

    @Test
    public void Add_NewSpartan_WithNegetiveNameGenderPhoneTest(){

        Spartan spartan=new Spartan("H","M","4656406");

        given().log().all().
                contentType(ContentType.JSON).
                body(spartan).
                when().post("/spartans").
                then().log().all().
                statusCode(400).
                log().all().
                body("error",is("Bad Request"));

    }

}