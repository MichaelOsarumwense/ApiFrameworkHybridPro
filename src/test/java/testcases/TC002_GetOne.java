package testcases;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_GetOne extends TestBase {

    @BeforeClass
    void getSingleEmployees() throws InterruptedException {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employee/" +empID);
        Thread.sleep(3000);
    }

    @Test
    void checkResponseBody(){
        logger.info("********** Checking Response Body************");
        String responseBody = response.getBody().asString();
        logger.info("Response Body==>"+responseBody);
        Assert.assertEquals(responseBody.contains(empID), true);
    }
    @Test
    void checkStatusCode(){
        logger.info("********** Checking Status Code************");
        int statusCode = response.statusCode();
        logger.info("Status code is ==>"+statusCode);
        Assert.assertEquals(statusCode, 200);
    }


    @Test
    void checkContentLength(){
        String ContentLength = response.header("Content-Length");
        Assert.assertTrue(Integer.parseInt(ContentLength)<1500);

    }
}
