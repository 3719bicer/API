package test;

import baseURL.DummyBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert.*;
import org.junit.Test;
import testData.TestDataDummy;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C20_Get_TestDataKullanimi extends DummyBaseURL {
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un;
    status code’unun 200,
    content Type’inin application/json ve
    body’sinin asagidaki gibi oldugunu test edin.

    Expoected Body
    //Response Body
    {
    "status":"success",
    "data": {
            "id": 3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */

    @Test
    public void get01() {
        // 1 - Request URL ve Body hazirla
        specDummy.pathParams("pp1", "employee", "pp2", 3);

        // 2 - Expected Data hazirla
        TestDataDummy testDataDummy = new TestDataDummy();
        JSONObject expData = testDataDummy.expectedDataBodyOlustur();

        // 3 - Response'u kaydet
        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}");

        // 4 - Assertion
        JsonPath respJP = response.jsonPath();
        assertEquals(testDataDummy.statusCode, response.getStatusCode());
        assertEquals(testDataDummy.contentType, response.contentType());
        assertEquals(expData.get("status"), respJP.get("status"));
        assertEquals(expData.get("message"), respJP.get("message"));
        assertEquals(expData.getJSONObject("data").get("id"), respJP.get("data.id"));
        assertEquals(expData.getJSONObject("data").get("employee_name"), respJP.get("data.employee_name"));
        assertEquals(expData.getJSONObject("data").get("employee_salary"), respJP.get("data.employee_salary"));
        assertEquals(expData.getJSONObject("data").get("employee_age"), respJP.get("data.employee_age"));
        assertEquals(expData.getJSONObject("data").get("profile_image"), respJP.get("data.profile_image"));
    }
    @Test
    public void get02(){
        /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un;
        status code’unun 200,
        content Type’inin application/json ve
        body’sinin asagidaki gibi oldugunu test edin.

        Expoected Body
        //Response Body
        {
        "status":"success",
        "data": {
                "id": 3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                },
        "message":"Successfully! Record has been fetched."
        }
        */
        //1- Request icin URL ve Body hazirla
        specDummy.pathParams("pp1","employee","pp2",3);

        //2-Expected Data hazirla.
        TestDataDummy testDataDummy=new TestDataDummy();
        JSONObject expjsonObject = testDataDummy.expectedDataBodyOlustur();
        System.out.println("expjsonObject = " + expjsonObject);

        //3-Responsu kaydet.
        Response response2=given()
                .contentType(ContentType.JSON)
                .spec(specDummy)
                .when()
                .get("/{pp1}/{pp2}");
        System.out.println("response2 = " + response2);
        response2.prettyPrint();

        //4-Assertion.

        JsonPath actResjsonPath=response2.jsonPath();

        assertEquals(testDataDummy.statusCode,response2.statusCode());
        assertEquals(testDataDummy.contentType,response2.contentType());
        assertEquals(expjsonObject.get("status"),actResjsonPath.get("status"));
        assertEquals(expjsonObject.get("message"),actResjsonPath.get("message"));
        assertEquals(expjsonObject.getJSONObject("data").get("id"),actResjsonPath.get("data.id"));
        assertEquals(expjsonObject.getJSONObject("data").get("employee_name"),actResjsonPath.get("data.employee_name"));
        assertEquals(expjsonObject.getJSONObject("data").get("employee_salary"),actResjsonPath.get("data.employee_salary"));
        assertEquals(expjsonObject.getJSONObject("data").get("employee_age"),actResjsonPath.get("data.employee_age"));
        assertEquals(expjsonObject.getJSONObject("data").get("profile_image"),actResjsonPath.get("data.profile_image"));

    }
}