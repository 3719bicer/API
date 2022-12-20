package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C04_Put_ResponseBilgileriAssertion {
    // TDD : Tester-driven Development
    /*
        https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki
        Json formatindaki body ile bir PUT request gonderdigimizde
                {
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
        donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin cloudflare,
            ve status Line’in HTTP/1.1 200 OK
    */

    @Test
    public void put01(){
        // 1 - Request icin Url ve Body hazirla
        String url = "https://jsonplaceholder.typicode.com/posts/70";
        JSONObject reqBody = new JSONObject();

        /*
           {
                "id" : 1,
                "title": "Cemile",
                "body": "Bardak",
                "userId": 3
            }
         */

        reqBody.put("title","Cemile");
        reqBody.put("body","Bardak");
        reqBody.put("userId",3);
        reqBody.put("id",1);
        System.out.println(reqBody);

        // 2 - Expected Data hazirla
        // 3 - Response kaydet
        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                put(url);
        response.prettyPrint(); // Bize burada JSON formatında response döndü.

        //when() : ilgili URL'ye benim olusturdugum body'i yolla ve put demek
        //given() : yollamış oldugum bu body'nin sonucunda ise bana icerigi bana
        //          JSON olarak bana gönder demek.Bunu bana en son response olarak ver.

        //Bize burada bir URL lazım yani hedef olarak nereyi degistireyim.
        //Bir de JSON Formatında Body lazım.

        // 4 - Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                header("Server","cloudflare").
                statusLine("HTTP/1.1 200 OK");


        //actualResponse.then().assertThat()...diyerek body'nin icindekiler veya statuscode
        //gibi Body'ye air bilgiler test edilir.
        //Ilerleyen derslerde expected data durum degisecek soyle ki;
        //mesela ben sadece dönen responsun Body'sine yonelik bilgileri test edeceksem
        //ActualResponse'u daa Path yapmama gerek kalmayacak zira expected Data yok.
        //ilerleyen dersler Response'u expexted Data'yı da test edecegim zaman
        //Path ile cevreleyecegim.

        //Hoca'nın NOTU:
        //1- Request icin URL ve Body hazirla
        //2- Expected Data Hazirla
        //3- Response kaydet
        //4- Assertion

        //*** given() :sonra preConditionlar
        //*** when()  :sonra ise yapilacak islemler gelir
    }

    @Test
    public void put02(){

        /*
        https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki
        Json formatindaki body ile bir PUT request gonderdigimizde
                {
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
        donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin cloudflare,
            ve status Line’in HTTP/1.1 200 OK
        */
        String URL2="https://jsonplaceholder.typicode.com/posts/70";
        JSONObject reqBody=new JSONObject();

        reqBody.put("title","Ahmet");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10);
        reqBody.put("id",70);

        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .put(URL2);

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","cloudflare")
                .statusLine("HTTP/1.1 200 OK");
    }
}
