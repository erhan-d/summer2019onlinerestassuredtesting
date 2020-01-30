package RestPractice;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SpartanRest_Test {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://54.205.50.162";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        // above will generate a base request URL of http://54.205.50.162:8000/api/
    }

    @Test
    public void Spartan_All_Test() {
        Response response = RestAssured.get("/spartans/");
        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("application/json;charset=UTF-8", response.getContentType());
        assertEquals("application/json;charset=UTF-8", response.getHeader("content-type"));
        boolean hasDate = response.getHeaders().hasHeaderWithName("Date");
        assertTrue(hasDate);

    }

    @Test
    public void SingleSpartanData_Test() {

        Response response = get("/spartans/2");
        System.out.println(response.asString()); // these 2 are same thing
        System.out.println(response.body().asString());// same with up
        response.prettyPrint();// it prints json format
        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.asString().contains("Nels"));
    }

    @Test
    public void Invalid_Spartan_Id_should_return_404_Test() {
        Response response = given().pathParam("id", 20000).
                when().get("/spartans/{id}");
        response.prettyPrint();
        assertEquals(404, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertTrue(response.asString().contains("Spartan Not Found"));

    }

    @Test
    public void SinglePartanDataWithHeader_test() {
        Response response = given().
                header("accept", "application/json").
                accept("application/json"). // these 3 is does same thing
                accept(ContentType.JSON).
                when().get("/spartans/2");
        assertEquals("application/json;charset=UTF-8", response.contentType());
    }

    @Test
    public void SingleSpartanDataWithHeader_XMLstatus_code_406_test() {
        Response response = given().
                accept(ContentType.XML).
                when().get("/spartans/2");
        System.out.println(response.statusLine());
        assertEquals(406, response.statusCode());
    }

    @Test
    public void Search_By_Providing_Query_Paramater() {
        Response response = given().
                accept(ContentType.JSON).
                param("gender", "female").
                when().get("/spartans/search");

        assertEquals(200, response.statusCode());
      //  assertFalse(response.asString().contains("ea"));

        System.out.println(response.path("content.name").toString());
        response.prettyPrint();
    }

    @Test
    public void SingleSpartanData_Json_FieldValue_Test() {
        Response response = given().
                accept(ContentType.JSON).
                pathParam("id", 2).
                when().get("/spartans/{id}");
        response.prettyPrint();
        System.out.println(response.path("name").toString());
        System.out.println(response.path("phone").toString());

        assertEquals("Nels",response.path("name").toString());

    }
}