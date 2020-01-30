package RestPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;




public class RestPractice {



    @Test
    @DisplayName("Hello end point")
    public void test1(){

        Response result=RestAssured.get("http://54.205.50.162:8000/api/hello");
        System.out.println(result.statusCode());
        System.out.println(result.asString());
        System.out.println(result.getHeader("content-length"));

        assertEquals(200,result.statusCode());
        assertEquals("Hello from Sparta",result.asString());
        assertEquals("text/plain;charset=UTF-8",result.header("content-type"));

         boolean dateHeader=result.getHeaders().hasHeaderWithName("Date");
         assertTrue(dateHeader);
         assertEquals("17",result.getHeader("content-length"));

    }
}
