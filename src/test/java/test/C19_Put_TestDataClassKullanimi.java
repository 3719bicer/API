package test;

import baseURL.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C19_Put_TestDataClassKullanimi extends JsonPlaceHolderBaseURL {
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip
    bir PUT request yolladigimizda donen response’in
    status kodunun 200,
    content type’inin “application/json; charset=utf-8”,
    Connection header degerinin “keep-alive”
    ve response body’sinin asagida verilen ile ayni oldugunu test ediniz.

    Request Body
    {
    "title":"Ali",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }

    Expected Data
    {
    "title":"Ali",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
    */

    @Test
    public void get01(){
        // 1 - Request URL ve Body olustur.(get icin body ihtiyaci yok)
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();
        JSONObject reqBody = testDataJsonPlaceHolder.requestBodyOlustur();

        System.out.println(reqBody);

        // 2 - Expected Data hazirla.
        JSONObject expData= testDataJsonPlaceHolder.requestBodyOlustur();

        // 3 - Response'u kaydet

        // Request' inizde bir body gönderiyorsanız, mutlaka göndereceginiz body'nin
        // data formatini belirtmek uzerre given'dan sonra COntentType'ini da belirtmek gerek!

        Response response= given().
                                spec(specJsonPlace).
                                contentType(ContentType.JSON).
                        when().
                                body(reqBody.toString()).
                                put("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4 - Assertion.

        JsonPath respJP = response.jsonPath();
        assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        assertEquals(testDataJsonPlaceHolder.contentType,response.contentType());
        assertEquals(testDataJsonPlaceHolder.connectionHeaderDegeri,response.header("Connection"));
        assertEquals(expData.get("title"),respJP.get("title"));
        assertEquals(expData.get("body"),respJP.get("body"));
        assertEquals(expData.get("userId"),respJP.get("userId"));
        assertEquals(expData.get("id"),respJP.get("id"));

    }

    @Test
    public void put02(){
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip
    bir PUT request yolladigimizda donen response’in
    status kodunun 200,
    content type’inin “application/json; charset=utf-8”,
    Connection header degerinin “keep-alive”
    ve response body’sinin asagida verilen ile ayni oldugunu test ediniz.

    Request Body
    {
    "title":"Ali",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }

    Expected Data
    {
    "title":"Ali",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
    */

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();
        JSONObject reqBody=testDataJsonPlaceHolder.requestBodyOlustur();
        System.out.println("reqBody = " + reqBody);

        // Request' inizde bir body gönderiyorsanız, mutlaka göndereceginiz body'nin
        // data formatini belirtmek uzerre given'dan sonra COntentType'ini da belirtmek gerek!
        Response response2=given()
                .spec(specJsonPlace)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .put("/{pp1}/{pp2}");

        response2.prettyPrint();

        JsonPath actResJP=response2.jsonPath();

        assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response2.getStatusCode());
        assertEquals(testDataJsonPlaceHolder.contentType,response2.contentType());
        assertEquals(testDataJsonPlaceHolder.connectionHeaderDegeri,response2.header("Connection"));
        assertEquals(reqBody.get("title"),actResJP.get("title"));
        assertEquals(reqBody.get("body"),actResJP.get("body"));
        assertEquals(reqBody.get("userId"),actResJP.get("userId"));
        assertEquals(reqBody.get("id"),actResJP.get("id"));

    }
}
