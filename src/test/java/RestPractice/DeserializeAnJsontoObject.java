package RestPractice;


import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
public class DeserializeAnJsontoObject extends TestBase{



    @Test
    public void DeserializeAnJsontoObject_Test(){

        Spartan sp1= get("/spartans/10").prettyPeek()
                .jsonPath().getObject("",Spartan.class);
        System.out.println(sp1);
//
//        Spartan sp2=get("/spartans/15").prettyPeek()
//                .jsonPath().getObject("",Spartan.class);
//        System.out.println(sp2);


    }
}
