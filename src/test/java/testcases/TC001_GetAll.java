package testcases;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_GetAll extends TestBase {
    @BeforeClass
    void getAllEmployees() throws InterruptedException {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
         httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employees");
        Thread.sleep(5);
    }

    @Test
    void checkResponseBody(){
        logger.info("********** Checking Response Body************");
        String responseBody = response.getBody().asString();
        logger.info("Response Body==>"+responseBody);
        Assert.assertTrue(responseBody!=null);
    }

    @Test
    void checkStatusCode(){
        logger.info("********** Checking Status Code************");
        int statusCode = response.statusCode();
        logger.info("Status code is ==>"+statusCode);
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    void checkResponseTime(){
        logger.info("********** Checking Response time************");
        long responseTime = response.getTime();
        logger.info("Response time is ==>"+responseTime);
        Assert.assertTrue(responseTime<=2000);
    }

    @Test
    void checkStatusLine(){
        //logger.info("********** Checking status line************");
        String StatusLine = response.getStatusLine();
        //logger.info("Status line is ==>"+StatusLine);
        Assert.assertEquals(StatusLine, "HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType(){
        logger.info("********** Checking content type************");
        String ContentType = response.header("Content-Type");
        logger.info("checkContentType is ==>"+ContentType);
        Assert.assertEquals(ContentType, "application/json;charset=utf-8");
    }

    @Test
    void checkServerType(){
        logger.info("********** Checking server time************");
        String serverTpe = response.header("server");
        logger.info("checkServerType is ==>"+serverTpe);
        Assert.assertEquals(serverTpe, "nginx/1.16.0");

    }

    @Test
    void checkCookies()
    {
        String cookie = response.getCookie("PHPSESSID");
    }

    @AfterClass
    void tearDown()
    {

    }


}
