package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.net.URI;

import static io.restassured.RestAssured.given;

public class C05_Get_ResponsBodyTesti {
        /*
        https://jsonplaceholder.typicode.com/posts/44 url'ine
        bir GET request yolladigimizda donen
        Response’in;
             status code'unun 200,
             ve content type'inin ContentType.JSON,
             ve response body'sinde bulunan userId'nin 5,
             ve response body'sinde bulunan title'in "optio dolor molestias sit"
             oldugunu test edin.
         */

    @Test
    public void get01(){
        //Kullanıcı Konak Hotel adli sitemizde rezervasyon yaptiginda POST Methodu ile WebService api miz
        //Database'e bu bilgileri kaydeder ve Response olarak da bana kayit tamamlanmistir demesini isterim.
        //Ya da WebServis Api şu söylenir: git bana su Id'nin tüm sutunlardaki satir bilgilerini bana
        //DATABASE imden getir.
        //e devlet in DataBase olarak ben sadece soy ismini degistirmek istiyoru dersem WebService Api
        //gidecek e devletin Database inden sadece soyadini guncelleyecek ama response olarak evet degistirdim
        //ama tüm bilgileri yeniden dondurmesi gerekiyorki hata var mi yok mu goreyim ve bilgileneyim.


        //1- Request icin gerekli URL ve body hazirla
        String url= "https://jsonplaceholder.typicode.com/posts/44";

        //2- Soruda verimesse Expected Datayı hazirla.
        //3- Respons'u kaydet.

        Response response= given().when().get(url);
        //response.prettyPrint();

        //4- Assertion

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("userId", Matchers.equalTo(5)).
                body("title",Matchers.equalTo("optio dolor molestias sit"));


        /*
        * Assertion daki then() in anlamı:
        * Karsilastirma ve eslestirecegimiz kosullarımız dan once kullanırız.
        * 1- Request icin URL ve Body hazirla
        * 2- Expected datayi hazirla
        * 3- Response kaydet
        * 4- Assertion
        */
    }

    @Test
    public void get02() {
        /*
        https://jsonplaceholder.typicode.com/posts/44 url'ine
        bir GET request yolladigimizda donen
        Response’in;
             status code'unun 200,
             ve content type'inin ContentType.JSON,
             ve response body'sinde bulunan userId'nin 5,
             ve response body'sinde bulunan title'in "optio dolor molestias sit"
             oldugunu test edin.
         */

        String URL2="https://jsonplaceholder.typicode.com/posts/44";
        Response response2=given().when().get(URL2);
        response2.prettyPrint();
        response2
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId",Matchers.equalTo(5))
                .body("title",Matchers.equalTo("optio dolor molestias sit"));


    }
}
