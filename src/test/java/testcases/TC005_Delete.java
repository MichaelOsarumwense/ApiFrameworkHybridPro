package testcases;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC005_Delete extends TestBase {
    @BeforeClass
    void updateEmployeeData() throws InterruptedException {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        //get an id
        response = httpRequest.request(Method.GET, "/employees");
       //capture the id
        JsonPath jsonPathEvaluator =response.jsonPath();
        String id=jsonPathEvaluator.get("[0].id");
        //delete the id data
        response = httpRequest.request(Method.DELETE, "/delete/"+ id);

        Thread.sleep(5000);

    }

    @Test
    void checkResponseBody(){
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
    }


    @Test
    void checkStatusCode(){
        logger.info("********** Checking Status Code************");
        int statusCode = response.statusCode();
        logger.info("Status code is ==>"+statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
