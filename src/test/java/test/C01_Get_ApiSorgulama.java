package test;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_Get_ApiSorgulama {
    /*
        https://restful-booker.herokuapp.com/booking/256884 url’ine
        bir GET request gonderdigimizde donen
        Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
    */

    @Test
    public void get01(){

        /*
        * NOT=
        * Nöbetci eczane web sitemizin API'sini üyemizin kullanimina actik,
        * üyemiz id ile arama yapip ilgili eczanenin bilgilerini istedi,
        * APİ gidip bu bilgileri database den getirip üyemize response olarak dönecek,
        * Normalde ona dönecek response bilgiler ile beklenilen bilgilerin eşit olması gerekir.
        */

        //1- Request icin Url ve Body hazirla.

        String url= "https://restful-booker.herokuapp.com/booking/256884";

        //2- Expected Datayı hazırla.

        //3- Response'u kaydet.

        Response response= given().when().get(url);
        response.prettyPrint();
        //Objeyi olusturduk(sol tarafı=) ve kaydettik(=sag tarafı).
        //End point imdeki degerleri getirdi ve kaydettik.

        /*
        {
            "firstname": "Josh",
                "lastname": "Allen",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
            "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
        },
            "additionalneeds": "super bowls"
        }
        */

        System.out.println("Status Code : " + response.getStatusCode());
        System.out.println("Content Type : " + response.getContentType());
        Headers headers = response.getHeaders();
        // ctrl+alt+v ile direk degiskenini olustutabiliriz.
        System.out.println("Server Header'inin Degeri : " + response.getHeader("Server"));
        System.out.println("Status Line : " + response.getStatusLine());
        System.out.println("Response suresi : " + response.getTime());

    }

    @Test
    public void get02(){
        //1- Request icin Url ve Body hazirla.
        String URL2="https://restful-booker.herokuapp.com/booking/234";

        //2- Expected Datayı hazırla.
        //3- Response'u kaydet.
        Response response=given().when().get(URL2);
        response.prettyPrint();

        //4- Assertion
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK");
    }
}
