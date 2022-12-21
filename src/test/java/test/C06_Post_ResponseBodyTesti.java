package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C06_Post_ResponseBodyTesti {
        /*
        https://jsonplaceholder.typicode.com/posts
        url’ine asagidaki body ile bir POST request gonderdigimizde

        {
        "title":"API",
        "body":"API ogrenmek ne guzel",
        "userId":10,
        }
        donen Response’un,

        status code’unun 201,
        ve content type’inin application/json
        ve Response Body'sindeki,
        "title"'in "API" oldugunu
        "userId" degerinin 100'den kucuk oldugunu
        "body" nin "API" kelimesi icerdigini

        test edin.
      */

    @Test
    public void post01(){
        // 1 - Request URL ve Body olustur
        String url = "https://jsonplaceholder.typicode.com/posts";

        /*
        {
        "title":"API",
        "body":"API ogrenmek ne guzel",
        "userId":10,
        }
         */
        JSONObject reqBody = new JSONObject();
        reqBody.put("title","API");
        reqBody.put("body","API ogrenmek ne guzel");
        reqBody.put("userId",10);
        System.out.println(reqBody);

        System.out.println("JSONObject :" + reqBody);
        //tostring diyerek ilgili nesneyi yani jsonobject nesnesini String e cevirerek POST ediyor.

        // 2 - Soruda verilmemisse Expected Data hazirla
        // 3 - Response 'u kaydet
        Response response = given().
                        contentType(ContentType.JSON).
                when().
                        body(reqBody.toString()).
                        post(url);
        response.prettyPrint();

        // 4 - Assertion
        response.
                then().
                assertThat().
                statusCode(201).
                contentType(ContentType.JSON).
                body("title", Matchers.equalTo("API")).
                body("userId",Matchers.lessThan(100)).
                body("body",Matchers.containsString("API"));

        //then() : karsilastiracagimiz ve eslestirecegimiz kosullarimiz
    }

    /*
    //TAB: secili satırları saga dogru 1 TAB iceri(SAGA) alir.
    //SHİFT TAB : SHIFT e basılı tutup TAB basılırsa 1 TAB dısa(SOLA) kayıdır.
    //Alt : Alt a basıp farenin sag tusuna basıp asagı yukarı yaparak istedgimiz kadar satırı secebilir
    //      ve secili satirlari TAB ile saga kaydırabiliriz.
    //      SHİFT tab ile de sola kaydırabiliriz.
    //Alt+SHIFT : ile de istedigimiz satirlari secip saga ve sola kaydırabiliriz.
    */

    @Test
    public void post02(){
        /*
        https://jsonplaceholder.typicode.com/posts
        url’ine asagidaki body ile bir POST request gonderdigimizde

        {
        "title":"API",
        "body":"API ogrenmek ne guzel",
        "userId":10,
        }
        donen Response’un,

        status code’unun 201,
        ve content type’inin application/json
        ve Response Body'sindeki,

        "title"'in "API" oldugunu
        "userId" degerinin 100'den kucuk oldugunu
        "body" nin "API" kelimesi icerdigini

        test edin.
      */

        //1- Request için URL ve Body hazirla
        String URL2="https://jsonplaceholder.typicode.com/posts";

        JSONObject reqBody=new JSONObject();
        reqBody.put("title","API");
        reqBody.put("body","API ogrenmek ne guzel");
        reqBody.put("userId",10);
        System.out.println(reqBody);

        //2- Expected Data yi hazirla
        //3- Response u kaydet
        Response response2=given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post(URL2);
        response2.prettyPrint();

        //4- Assertion

        response2
                .then()
                .assertThat()
                .statusCode(201)
                .contentType("application/json")
                .body("title",Matchers.equalTo("API"))
                .body("userId",Matchers.lessThan(100))
                .body("title",Matchers.containsString("API"));
    }
}
