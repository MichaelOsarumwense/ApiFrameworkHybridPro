package testcases;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RestUtils;

public class TC003_Post extends TestBase {

    String empName= RestUtils.empName();
    String empSalary=RestUtils.empSalary();
    String empAge=RestUtils.empAge();

    @BeforeClass
    void createNewEmployee() throws InterruptedException {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name",empName);
        requestParams.put("salary",empSalary);
        requestParams.put("age",empAge);

        httpRequest.header("Content-Type","application/json");

        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST, "/create");
        Thread.sleep(5000);

    }

    @Test
    void checkResponseBody(){
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(empName),true);
        Assert.assertEquals(responseBody.contains(empSalary),true);
        Assert.assertEquals(responseBody.contains(empAge),true);
    }


    @Test
    void checkStatusCode(){
        logger.info("********** Checking Status Code************");
        int statusCode = response.statusCode();
        logger.info("Status code is ==>"+statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
