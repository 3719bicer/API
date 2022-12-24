package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C14_Put_SoftAssertIleExpectedDataTesti {
    /*
    http://dummy.restapiexample.com/api/v1/update/21 url’ine asagidaki
    body’ye sahip bir PUT request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

    Request Body
    {
        "status":"success",
        "data":{
                "name":“Ahmet",
                "salary":"1230",
                "age":"44",
                "id":40
                }
    }

    Response Body
    {"status":"success",
    "data":{
        "status":"success",
        "data":{
                "name":“Ahmet",
                "salary":"1230",
                "age":"44",
                "id":40
                }
           },
    "message":"Successfully! Record has been updated."
    }
    */

    @Test
    public void put01(){
        // 1 - URL ve body olustur, Put metodu icin body gerekli
        String url = "http://dummy.restapiexample.com/api/v1/update/21";

        /*
        Request Body
            {
                "status":"success",
                "data":{
                        "name":“Ahmet",
                        "salary":"1230",
                        "age":"44",
                        "id":40
                        }
            }
         */
        JSONObject expInnerData2 = new JSONObject();
        JSONObject reqBody = new JSONObject();
        expInnerData2.put("name","Ahmet");
        expInnerData2.put("salary","1230");
        expInnerData2.put("age","44");
        expInnerData2.put("id",40);
        reqBody.put("data",expInnerData2);
        reqBody.put("status","success");
        System.out.println("reqBody = " + reqBody);

        // 2 - Expected Data hazirla
        /*
        {
            "status":"success",
            "data":{
                "status":"success",
                "data":{
                        "name":“Ahmet",
                        "salary":"1230",
                        "age":"44",
                        "id":40
                        }
                   },
            "message":"Successfully! Record has been updated."
            }
         */
        JSONObject expData = new JSONObject();
        JSONObject expInnerData=new JSONObject();
        JSONObject expInnerDataInner=new JSONObject();

        expInnerDataInner.put("name","Ahmet");
        expInnerDataInner.put("salary","1230");
        expInnerDataInner.put("age","44");
        expInnerDataInner.put("id",40);

        expInnerData.put("status","success");
        expInnerData.put("data",expInnerDataInner);

        expData.put("status","success");
        expData.put("data",expInnerData);
        expData.put("message","Successfully! Record has been updated.");
        System.out.println("expData = " + expData);

        // 3 - Response'u kaydet
        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                put(url);
        System.out.println("response.prettyPrint() = " + response.prettyPrint());
        // 4 - Assertion
        JsonPath respJP = response.jsonPath();
        System.out.println("respJP = " + respJP);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(respJP.get("status"), expData.get("status"));
        softAssert.assertEquals(respJP.get("message"), expData.get("message"));
        softAssert.assertEquals(respJP.get("data.data.name"),expData.getJSONObject("data").getJSONObject("data").get("name"));
        softAssert.assertEquals(respJP.get("data.data.id"),expData.getJSONObject("data").getJSONObject("data").get("id"));
        softAssert.assertEquals(respJP.get("data.data.salary"),expData.getJSONObject("data").getJSONObject("data").get("salary"));
        softAssert.assertEquals(respJP.get("data.data.age"),expData.getJSONObject("data").getJSONObject("data").get("age"));
        softAssert.assertEquals(respJP.get("data.status"),expData.getJSONObject("data").get("status"));
        softAssert.assertAll();
    }

    @Test
    public void put2(){
        //21 id ye sahip bilgilerin güncellemek istiyorum.
        //Bilgileri request body deki yapmak istiyorum ve kaydediyorum.
        //Bunu ilgili API URL ve body method u algılayarak bu bilgileri Database kayit ediyor.
        //Ve bilgileri basarili bir sekilde kaydederek ilgili id bilgilerini bize ayrintili bir sekilde dönüyor.

        /*
    http://dummy.restapiexample.com/api/v1/update/21 url’ine asagidaki
    body’ye sahip bir PUT request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

    Request Body
    {
        "status":"success",
        "data":{
                "name":“Ahmet",
                "salary":"1230",
                "age":"44",
                "id":40
                }
    }

    Response Body
    {
    "status":"success",
    "data":{
        "status":"success",
        "data":{
                "name":“Ahmet",
                "salary":"1230",
                "age":"44",
                "id":40
                }
           },
    "message":"Successfully! Record has been updated."
    }
    */
        //1- Request icin URL ve Body hazirla
        /*Request Body
        {
            "status":"success",
             "data":{
                    "name":“Ahmet",
                    "salary":"1230",
                    "age":"44",
                    "id":40
        }
        }*/
        String URL2="http://dummy.restapiexample.com/api/v1/update/21";

        JSONObject reqBody=new JSONObject();
        JSONObject dataInnerReqBody=new JSONObject();

        dataInnerReqBody.put("name","Ahmet");
        dataInnerReqBody.put("salary","1230");
        dataInnerReqBody.put("age","44");
        dataInnerReqBody.put("id",40);
        reqBody.put("status","success");
        reqBody.put("data",dataInnerReqBody);
        System.out.println("reqBody = " + reqBody);

        //2- Expected Data hazirla
        /*Response Body
        {
        "status":"success",
         "data":{
                "status":"success",
                "data":{
                        "name":“Ahmet",
                        "salary":"1230",
                        "age":"44",
                        "id":40
            }
        },
            "message":"Successfully! Record has been updated."
        }*/

        JSONObject expData = new JSONObject();
        JSONObject expInnerData=new JSONObject();
        JSONObject expInnerDataInner=new JSONObject();

        expInnerDataInner.put("name","Ahmet");
        expInnerDataInner.put("salary","1230");
        expInnerDataInner.put("age","44");
        expInnerDataInner.put("id",40);

        expInnerData.put("status","success");
        expInnerData.put("data",expInnerDataInner);

        expData.put("status","success");
        expData.put("data",expInnerData);
        expData.put("message","Successfully! Record has been updated.");
        System.out.println("expData = " + expData);

        //3- Response kaydet
        Response response2=given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .put(URL2);
        System.out.println("response2.prettyPrint() = " + response2.prettyPrint());

        //4- Assertion
        JsonPath actResJsonPath=response2.jsonPath();
        System.out.println("actResJsonPath = " + actResJsonPath);

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(actResJsonPath.get("status"),expData.get("status"));
        softAssert.assertEquals(actResJsonPath.getString("message"),expData.get("message"));
        softAssert.assertEquals(actResJsonPath.getString("data.status"),expData.getJSONObject("data").get("status"));
        softAssert.assertEquals(actResJsonPath.get("data.data.name"),expData.getJSONObject("data").getJSONObject("data").get("name"));
        softAssert.assertEquals(actResJsonPath.get("data.data.salary"),expData.getJSONObject("data").getJSONObject("data").get("salary"));
        softAssert.assertEquals(actResJsonPath.get("data.data.age"),expData.getJSONObject("data").getJSONObject("data").get("age"));
        softAssert.assertEquals(actResJsonPath.get("data.data.id"),expData.getJSONObject("data").getJSONObject("data").get("id"));

        softAssert.assertAll();

    }
}
