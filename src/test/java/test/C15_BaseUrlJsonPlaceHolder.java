package test;
import baseURL.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class C15_BaseUrlJsonPlaceHolder extends JsonPlaceHolderBaseURL {
    //Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin
    @Test
    public void get01(){
        /*
        URL de soru isaretinden sonra gelenler Query yani talep variable inin paramaetreleri iken,
        Base URL den sonra gelenler ise path in parametresi dir.
        Soru isaretinden sonra ket ve value lar gelmektedir.
        PATH parametresi ise ilgili sitenin bir baska bolumu anlamı olabilir.Yaqni amazon/arama vb.
        Parametreleri ic ice bir yapi gibi dusunebiliriz.
        Bir resmi kurumun muhasebe yetkilisi ile görüsebilmek icin
        önce güvenlik/danisma/5.kat/101.oda gibi düsünülebilir.

        Query parametresi kendisiniden bir onceki slash in adresinde kalir ve parametrelerini oraya yerlestirir.

         */

        /*
        1-  https://jsonplaceholder.typicode.com/posts endpointine bir GET
         request gonderdigimizde donen response’un status code’unun 200 oldugunu
         ve Response’ta 100 kayit oldugunu test edin.
        */

        //URL de slash den sonraki degerimiz "posts" bir parametredir.
        //bir slash ve bir deger daha olsaydı o da 2. parametre olcaktı.
        //Slash dan öncesi de bizim BAse URL imizdir.Dinamik hale getirebilmek icin bir packege düzenledik
        //ve base URL mizi cagıracagımız bir JsonPLaceHolderBaseURl clası actık ve BAse URl tanımladik.


        // 1 - Url ve body hazirla, get metodunda body ihtiyacimiz yok
        specJsonPlace.pathParam("pp1","posts");
        // 2 - Expected Data hazirla
        // 3 - Response'u kaydet
        Response response = given().
                spec(specJsonPlace).
                when().
                get("/{pp1}");
        response.prettyPrint();
        // 4 - Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("title", hasSize(100));
    }

    @Test
    public void get01Repeat(){
        /*
        1-  https://jsonplaceholder.typicode.com/posts endpointine bir GET
         request gonderdigimizde donen response’un status code’unun 200 oldugunu
         ve Response’ta 100 kayit oldugunu test edin.
        */

        specJsonPlace.pathParams("pp1","posts");
        Response response=given()
                .spec(specJsonPlace)
                .when()
                .get("/{pp1}");
        //System.out.println("response.prettyPrint() = " + response.prettyPrint());

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title",hasSize(100));
    }

    @Test
    public void get02(){
        /*
        2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET
            request gonderdigimizde donen response’un status code’unun 200 oldugunu
            ve “title” degerinin “optio dolor molestias sit” oldugunu test edin
         */
        // 1 - Url ve body hazirla, get metodunda body ihtiyacimiz yok
        specJsonPlace.pathParams("pp1","posts","pp2",44);
        // 2 - Expected Data hazirla
        // 3 - Response'u kaydet
        Response response = given().
                spec(specJsonPlace).
                when().
                get("/{pp1}/{pp2}");
        response.prettyPrint();
        // 4 - Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("title",equalTo("optio dolor molestias sit"));
    }

    @Test
    public void get02Repeat(){
        /*
        2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET
            request gonderdigimizde donen response’un status code’unun 200 oldugunu
            ve “title” degerinin “optio dolor molestias sit” oldugunu test edin
         */
        //1- Rquest icin URL ve Bosy hazirla
        specJsonPlace.pathParams("pp1","posts","pp2",44);

        //2- Expected Data hazirla
        //3- Response kaydet
        Response response=given()
                .spec(specJsonPlace)
                .when()
                .get("/{pp1}/{pp2}");
        System.out.println("response = " + response);
        //4- Asssertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title",equalTo("optio dolor molestias sit"));
    }

    @Test
    public void delete01(){
       /*
        3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE
            request gonderdigimizde donen response’un status code’unun 200 oldugunu ve
            response body’sinin null oldugunu test edin
         */
        // 1 - URL ve body hazirligi
        specJsonPlace.pathParams("pp1","posts","pp2",50);
        // 2 - Expected Data hazirla
        // 3 - Response'u kaydet
        Response response = given().
                spec(specJsonPlace).
                when().
                delete("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4 - Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("body",nullValue());
    }
}
