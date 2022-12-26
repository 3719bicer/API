package test;

import baseURL.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C18_Get_TestDataClassKullanimi extends JsonPlaceHolderBaseURL {
    /*
    C18_Get_TestDataClassKullanimi
    https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
    request yolladigimizda donen response’in status kodunun 200 ve
    response body’sinin asagida verilen ile ayni oldugunutest ediniz.

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
        // 1 - Request URL ve Body hazirla, get metodu icin body ihtiyacimiz yok
        specJsonPlace.pathParams("pp1","posts","pp2",22);

        // 2 - Expected Data hazirla
        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();
        JSONObject expData;
        expData = testDataJsonPlaceHolder.expectedDataOlustur(3,22,"dolor sint quo a velit explicabo quia nam",
                "eos qui et ipsum ipsam suscipit aut" +
                "\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit" +
                "\nnam impedit esse");

        // 3 - Response'u kaydet
        Response response = given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4 - Assertion
        JsonPath actResJP = response.jsonPath();
        assertEquals(200,response.getStatusCode());
        assertEquals(expData.get("userId"),actResJP.get("userId"));
        assertEquals(expData.get("id"),actResJP.get("id"));
        assertEquals(expData.get("title"),actResJP.get("title"));
        assertEquals(expData.get("body"),actResJP.get("body"));
    }

    @Test
    public void get02(){
        /*
        C18_Get_TestDataClassKullanimi
        https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
        request yolladigimizda donen response’in status kodunun 200 ve
        response body’sinin asagida verilen ile ayni oldugunutest ediniz.

        Response body :
        {
        "userId":3,
        "id":22,
        "title":"dolor sint quo a velit explicabo quia nam",
        "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
        um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
        */
        specJsonPlace.pathParams("pp1","posts","pp2","22");

        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();

        JSONObject expData=testDataJsonPlaceHolder.expectedDataOlustur(3,22,"dolor sint quo a velit explicabo quia nam",
                "eos qui et ipsum ipsam suscipit aut" +
                        "\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit" +
                        "\nnam impedit esse");
        System.out.println("expData = " + expData);

        Response response2=given()
                .spec(specJsonPlace)
                .when()
                .get("/{pp1}/{pp2}");
         response2.prettyPrint();

        JsonPath actResJP=response2.jsonPath();

        assertEquals(200,response2.getStatusCode());
        assertEquals(expData.getInt("userId"),actResJP.getInt("userId"));
        assertEquals(expData.getInt("id"),actResJP.getInt("id"));
        assertEquals(expData.get("title"),actResJP.getString("title"));
        assertEquals(expData.get("body"),actResJP.getString("body"));
    }
}
