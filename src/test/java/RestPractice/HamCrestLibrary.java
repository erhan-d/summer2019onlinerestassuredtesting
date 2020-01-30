package RestPractice;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class HamCrestLibrary {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://54.205.50.162";
        RestAssured.port = 1000;
        RestAssured.basePath = "/api";
        // above will generate a base request URL of http://54.205.50.162:8000/api/
    }

    @Test
    public void Calculation_Test(){
        int a= 10, b=20;
        assertEquals(30,a+b);

        assertThat(30, equalTo(a+b));
        assertThat(40,greaterThan(a+b));
    }

    @Test
    public void arrayTest(){

        given().pathParam("my_id",3).
                when().get("/spartans/{my_id}").
                then().assertThat().
                statusCode(equalTo(200)).
                contentType(ContentType.JSON).
                body("id",equalTo(3)).
                body("gender",equalTo("male")).
                body("phone",hasToString("6105035231"));


    }
}
