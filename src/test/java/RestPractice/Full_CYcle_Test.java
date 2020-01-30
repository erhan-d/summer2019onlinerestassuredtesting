package RestPractice;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Full_CYcle_Test extends TestBase{

    @Test
    public void getSpartan_Test(){
        int spartanID=createRandomSpartan();
        System.out.println(spartanID);
    }

    public int createRandomSpartan() {

        Faker faker=new Faker();

        int randomIndex=faker.number().numberBetween(0,2);
        String [] genders={"Male","Female"};
        Spartan spartan=new Spartan(faker.name(),"Male",
                Long.parseLong(faker.number().digits(11)));

        return  given().log().all().
                contentType(ContentType.JSON).
                body(spartan).
                when().post("/spartans").
                prettyPeek()
                .jsonPath()
                .getInt("data.id");


    }
}
