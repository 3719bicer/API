package test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert.*;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
public class C11_Get_ExpectedDataOlusturma {
        /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine
        bir GET request yolladigimizda donen response body’sinin
        asagida verilen ile ayni oldugunu test ediniz.

        Response body :
        {
        "userId":3,
        "id":22,
        "title":"dolor sint quo a velit explicabo quia nam",
        "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
        um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
       */

    @Test
    public void get01(){
        // 1 - Request URL ve body hazirla
        String url = " https://jsonplaceholder.typicode.com/posts/22";

        // 2 - Expected Data hazirla
        JSONObject expBody = new JSONObject();
        expBody.put("userId",3);
        expBody.put("id",22);
        expBody.put("title","dolor sint quo a velit explicabo quia nam");
        expBody.put("body","eos qui et ipsum ipsam suscipit aut" +
                "\nsed omnis non odio" +
                "\nexpedita earum mollitia molestiae aut atque rem suscipit" +
                "\nnam impedit esse");

        // 3 - Response'u kaydet
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4 - Assertion
        // Oncelikle yapilmasi gereken sey Response'u JsonPath objesine cevirmek
        // Dönen Response ile exp Response un karsilastirmaya musait hale gelebilmesi icin
        // dönen Responsu JaonPAth ile cevrelemem gerekit.

        JsonPath respJSPath = response.jsonPath();
        assertEquals(expBody.get("userId"),respJSPath.getInt("userId"));
        assertEquals(expBody.get("id"),respJSPath.getInt("id")); // getInt: Donus tipi ne ise ona gore yazılır.
        assertEquals(expBody.get("title"),respJSPath.getString("title"));
        assertEquals(expBody.get("body"),respJSPath.getString("body"));

        assertEquals(expBody.get("userId"),respJSPath.get("userId"));
        //get: <T> T : Response dan doneni otamatik olarak ceviriyor.
        //      Generics ile yazılmıs.(Dinamik)
        //      Method da donus tipi olarak her obje ile calisir
        //getInt :primitive  tipinde Int olarak donuyor.
    }


    @Test
    public void get02(){
        /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine
        bir GET request yolladigimizda donen response body’sinin
        asagida verilen ile ayni oldugunu test ediniz.

        Response body :
        {
        "userId":3,
        "id":22,
        "title":"dolor sint quo a velit explicabo quia nam",
        "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
        um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
       */

        // 1 - Request URL ve body hazirla
        String URL2="https://jsonplaceholder.typicode.com/posts/22";

        // 2 - Expected Data hazirla
        JSONObject expData=new JSONObject();

        expData.put("userId",5);
        expData.put("id",22);
        expData.put("title","dolor sint quo a velit explicabo quia nam");
        expData.put("body","eos qui et ipsum ipsam suscipit aut" +
                "\nsed omnis non odio" +
                "\nexpedita earum mollitia molestiae aut atque rem suscipit" +
                "\nnam impedit esse");
        System.out.println("expData : " + expData);

        // 3 - Response'u kaydet
        Response response2=given().when().get(URL2);
        response2.prettyPrint();

        // 4 - Assertion
        JsonPath actDataResponse=response2.jsonPath();

        assertEquals("userId does not match",expData.get("userId"),actDataResponse.getInt("userId"));
        assertEquals("id does not match",expData.get("id"),actDataResponse.getInt("id"));
        assertEquals("title does not match",expData.get("title"),actDataResponse.getString("title"));
        assertEquals("body does not match",expData.get("body"),actDataResponse.getString("body"));

        /*SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(expData.get("userId"),actDataResponse.getInt("userId"));
        softAssert.assertEquals(expData.get("id"),actDataResponse.getInt("id"));
        softAssert.assertEquals(expData.get("title"),actDataResponse.getString("title"));
        softAssert.assertEquals(expData.get("body"),actDataResponse.getString("body"));*/

    }
}
