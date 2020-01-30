package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SpartanRest_Weekend {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://54.205.50.162";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        // above will generate a base request URL of http://54.205.50.162:8000/api/
    }

    @Test
    public void test1() {

        Response response = given().
                accept(ContentType.JSON).
                when().get("/spartans/2");
        response.prettyPrint();
        assertEquals("Male", response.path("gender").toString());

    }

    @Test
    public void test2() {
        Response response = given().
                accept(ContentType.JSON).
                queryParam("gender", "male").
                when().get("/spartans/search");

        assertEquals(200, response.statusCode());
        assertFalse(response.asString().contains("Female"));

        System.out.println(response.path("pageable.sort.empty").toString());
        boolean isEmpty = response.jsonPath().getBoolean("pageable.sort.empty");
        assertTrue(isEmpty, "Assertion for empty has failed");
        int total = response.jsonPath().getInt("totalElements");
        System.out.println(total);
        response.prettyPrint();


    }

    @Test
    public void test3() {
        Response response = given().
                accept(ContentType.JSON).
                queryParam("gender", "male").
                when().get("/spartans/search");

        assertEquals(200, response.statusCode());
        assertFalse(response.asString().contains("Female"));
        long firstPhone= response.jsonPath().getLong("content[0].phone");
        System.out.println(firstPhone);

        List <Long> phoneList=response.jsonPath().getList("content.phone");
        List <String> nameList=response.jsonPath().getList("content.name");
        System.out.println(phoneList);
        System.out.println(nameList);

    }
}