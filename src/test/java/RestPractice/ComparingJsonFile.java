package RestPractice;

import com.automation.pojos.Job;
import com.automation.pojos.Location;
import com.automation.utilities.ConfigurationReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.path.json.JsonPath;
import org.apache.http.util.Asserts;
import org.junit.Assert;
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



public class ComparingJsonFile {

   @Test
    public void compare2File() throws IOException {
    //1.way
        Gson gson = new Gson();
        //just read the files
        FileReader file1 = new FileReader("src/test/resources/test_data/spartan1.json");
        FileReader file2 = new FileReader("src/test/resources/test_data/spartan2.json");
        // deserialization json to map Object
        Map one = gson.fromJson(file1, Map.class);
        Map two = gson.fromJson(file2, Map.class);
        Assert.assertThat(one,equalTo(two));

     //2.way
       ObjectMapper objectMapper=new ObjectMapper();

       JsonNode jsonNode1=objectMapper.readTree(file1);
       JsonNode jsonNode2=objectMapper.readTree(file2);
       Assert.assertThat(jsonNode1,equalTo(jsonNode2));

       }

    }

