package test;

import baseURL.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C22_Put_DeSerialization extends JsonPlaceHolderBaseURL {

    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
    body’e sahip bir PUT request yolladigimizda donen response’in
    response body’sinin asagida verilen ile ayni oldugunu test ediniz.

    Request Body
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }

    Expected Data :
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }

     NOTLAR;
     - //İslem yaptigimiz nesneyi , saklamak istedigimiz formata cevirme islemine SERIALIZATION denir.
     - //Java objelerini API sorgulari yapmak uzere JSON objesine cevirmeye Serialization deenir.
     - //Ornegin MAP leri objelere cevirme islemi Serialization senir.

     - //Donen JSON objesini testlerimizde kullanmak üzere Java objesine cevirmewye (MAP)de de-Serialization denir.
     - //mesela sorgu sonucunda bize dönen Response u JAva da mevcut bir objeye donusturlim ki karsilastirma
         ve teste musaıt hale gelsin.
     */

    @Test
    public void put01(){
        // 1- Request icin URL ve Body hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",70);
        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();

        HashMap<String,Object> reqBodyMap = testDataJsonPlaceHolder.requestBodyOlusturMap();
        System.out.println("reqBodyMap = " + reqBodyMap);

        // 2- Expected Data olustur

        HashMap<String,Object> expBodyMap = testDataJsonPlaceHolder.requestBodyOlusturMap();
        System.out.println("expBodyMap = " + expBodyMap);

        // 3- Response'u kaydet
        // Not : Request Body'i Map olarak hazirladigimiz icin ve Map de Java nin
        // kendisine ait bir format oldugu icin Response yollanirken toString
        // metoduna ihtiyac kalmaz!!!

        Response response = given().
                spec(specJsonPlace).
                contentType(ContentType.JSON).
                when().
                body(reqBodyMap).
                put("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4- Assertion

        HashMap <String,Object> respMap = response.as(HashMap.class);

        assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.statusCode());
        assertEquals(expBodyMap.get("title"),respMap.get("title"));
        assertEquals(expBodyMap.get("body"),respMap.get("body"));
        assertEquals(expBodyMap.get("userId"),respMap.get("userId"));
        assertEquals(expBodyMap.get("id"),respMap.get("id"));
    }
    @Test
    public void put02(){
        // Class ımız JsonPlaceHolder a extend edildigi icin o class dan variableri cagirabiliriz.
        // 1- Request icin URL ve Body hazirla
        URL= "https://jsonplaceholder.typicode.com/posts/70";

        // 2- Expected Data olustur
        // Hash Map tipinde Expected Data yi olusturmak gerekir.
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();

        testDataJsonPlaceHolder.title="Ahmet";
        testDataJsonPlaceHolder.body="Merhaba";
        testDataJsonPlaceHolder.userId=10;
        testDataJsonPlaceHolder.id=70;

        HashMap expectedResponse= testDataJsonPlaceHolder.mapJsonOlusutr();
        System.out.println("expectedResponse = " + expectedResponse);

        //Simdi ise Response olusturalim.

        testDataJsonPlaceHolder.title="Ahmet";
        testDataJsonPlaceHolder.body="Merhaba";
        testDataJsonPlaceHolder.userId=10.0;
        testDataJsonPlaceHolder.id=70.0;
        HashMap requestJsonObject=testDataJsonPlaceHolder.mapJsonOlusutr();
        System.out.println("requestJsonObject = " + requestJsonObject);

        // 3- Response'u kaydet
        Response response2=given()
                .contentType(ContentType.JSON)
                .spec(specJsonPlace)
                .when()
                .body(requestJsonObject)
                .put("/{pp1}/{pp2}");

        System.out.println("response2.prettyPrint() = " + response2.prettyPrint());
        //Response olustu.
        /*
        * Yukarida her iki obje de HASHMAP; fakat bize dönen bilgi artık JSON yani Java nin olmayan
        * bir obje ile dönüyor.Cunkü response bolumunde zaten bana JSON olarak don diyorum.
        * Ama karislastirma yapabilmesi icin dönen JSON'i ben HashMap e döndürmeliyim ki
        * HAshMap olan expected data ile karsilastirabileyim.
        */

        // 4- Assertion
        HashMap actualResponse= response2.as(HashMap.class);

        assertEquals(expectedResponse.get("title"),actualResponse.get("title"));
        assertEquals(expectedResponse.get("body"),actualResponse.get("body"));
        assertEquals(expectedResponse.get("userId"),actualResponse.get("userId"));
        assertEquals(expectedResponse.get("id"),actualResponse.get("id"));
    }
}
