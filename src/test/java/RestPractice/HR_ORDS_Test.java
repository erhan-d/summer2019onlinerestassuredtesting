package RestPractice;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HR_ORDS_Test {

    @BeforeClass
    public static void setup() {
        baseURI = "http://54.205.50.162";
        port = 1000;
        basePath = "/ords/hr";
        // above will generate a base request URL of http://54.205.50.162/ords/hr/regions
    }

    @Test
    public void test_regions() {
        Response response = get("http://54.205.50.162:1000/ords/hr/regions");
        response.prettyPrint();
        String first_regionName = response.jsonPath().getString("items.region_name");
        System.out.println(first_regionName);

        String href = response.jsonPath().getString("items[1].links[0].href");
        System.out.println(href);

        String lasthref = response.jsonPath().getString("links[3].rel");
        System.out.println(lasthref);

        List <String> listhref = response.jsonPath().getList("items.links.href");
        System.out.println(href);

        assertEquals(200, response.statusCode());
    }


    @AfterClass
    public static void teardown() {
        RestAssured.reset();
    }

}
