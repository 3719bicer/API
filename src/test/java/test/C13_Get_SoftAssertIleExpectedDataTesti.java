package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C13_Get_SoftAssertIleExpectedDataTesti {
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

    Response Body
        {
        "status":"success",
        "data":{
            "id":3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
    */

    @Test
    public void get01(){

        // 1 - Request icin URL ve Body olustur, Get request icin Body ihtiyaci yok

        String url = "http://dummy.restapiexample.com/api/v1/employee/3";
        //Ger request imiz JSON formatinda
        //once en icteki JSON lardan baslanmali.

        // 2 - Expected Data hazirla
        JSONObject data = new JSONObject();
        JSONObject expBody = new JSONObject();

        data.put("id",3);
        data.put("employee_name","Ashton Cox");
        data.put("employee_salary",86000);
        data.put("employee_age",66);
        data.put("profile_image","");

        expBody.put("status","success");
        expBody.put("data",data);
        expBody.put("message","Successfully! Record has been fetched.");
        System.out.println("expBody = " + expBody);

        // 3 - Response' u kaydet
        Response response = given().when().get(url);

        response.prettyPrint();

        // 4 - Assertion
        JsonPath respJP = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(respJP.get("status"),expBody.get("status"));
        softAssert.assertEquals(respJP.get("data.id"),expBody.getJSONObject("data").get("id"));
        softAssert.assertEquals(respJP.get("data.employee_name"),expBody.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(respJP.get("data.employee_salary"),expBody.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(respJP.get("data.employee_age"),expBody.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(respJP.get("data.profile_image"),expBody.getJSONObject("data").get("profile_image"));
        softAssert.assertEquals(respJP.get("message"),expBody.get("message"));
        softAssert.assertAll();
    }
    @Test
    public void get02(){
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

    Response Body
        {
        "status":"success",
        "data":{
            "id":3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
        "message":"Successfully! Record has been fetched."
        }
    */
        String URL2="http://dummy.restapiexample.com/api/v1/employee/3";

        JSONObject expBody=new JSONObject();
        JSONObject innerExpBody=new JSONObject();

        innerExpBody.put("id",3);
        innerExpBody.put("employee_name","Ashton Cox");
        innerExpBody.put("employee_salary",86000);
        innerExpBody.put("employee_age",66);
        innerExpBody.put("profile_image","");

        expBody.put("status","success");
        expBody.put("data",innerExpBody);
        expBody.put("message","Successfully! Record has been fetched.");
        System.out.println("expBody = " + expBody);

        Response response2=given().contentType(ContentType.JSON).when().get(URL2);
        response2.prettyPrint();
        //Request imiz ilgili API a iletildi.API da Database den bu bilgileri cekip API a iletti.
        //API da client yani response talep eden kisiye bu bilgileri gösterdi.

        JsonPath actResponseJsonPath=response2.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(actResponseJsonPath.get("data.id"),expBody.getJSONObject("data").get("id"));
        softAssert.assertEquals(actResponseJsonPath.get("data.employee_name"),expBody.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(actResponseJsonPath.get("data.employee_salary"),expBody.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(actResponseJsonPath.get("data.employee_age"),expBody.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(actResponseJsonPath.get("data.profile_image"),expBody.getJSONObject("data").get("profile_image"));

        softAssert.assertEquals(actResponseJsonPath.get("status"),expBody.get("status"));
        softAssert.assertEquals(actResponseJsonPath.getString("message"),expBody.getString("message"));

    }
}
