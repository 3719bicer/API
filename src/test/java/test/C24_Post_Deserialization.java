package test;
import baseURL.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testData.TestDataHerokuApp;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_Post_Deserialization extends HerokuAppBaseUrl {
        /*
          https://restful-booker.herokuapp.com/booking url'ine asagidaki
          body'ye sahip bir POST request gonderdigimizde donen response'un
          id haric asagidaki gibi oldugunu test edin.

            Request body
            {
            "firstname" : "Ali",
            "lastname" : "Bak",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                     "checkin" : "2021-06-01",
                     "checkout" : "2021-06-10"
                              },
            "additionalneeds" : "wi-fi"
            }

           Response Body
           {
           "bookingid":24,
           "booking":{
               "firstname":"Ali",
               "lastname":"Bak",
               "totalprice":500,
               "depositpaid":false,
               "bookingdates":{
                   "checkin":"2021-06-01",
                   "checkout":"2021-06-10"
               },
               "additionalneeds":"wi-fi"
               }
           }
       */

    @Test
    public void post01(){
        // 1- Request icin URL ve Body hazirla
        specHerokuApp.pathParam("pp1","booking");
        TestDataHerokuApp testDataHerokuApp = new TestDataHerokuApp();
        HashMap<String,Object> reqBodyMap = testDataHerokuApp.requestBodyOlusturMap();
        System.out.println("reqBodyMap = " + reqBodyMap);

        // 2- Expected Data hazirla
        HashMap<String,Object> expDataMap = testDataHerokuApp.expectedBodyOlusturMap();
        System.out.println("expDataMap = " + expDataMap);

        // 3- Response'i kaydet
        Response response = given().
                spec(specHerokuApp).
                contentType(ContentType.JSON).
                when().
                body(reqBodyMap).
                post("/{pp1}");
        response.prettyPrint();

        // 4- Assertion
        HashMap <String ,Object> respMap = response.as(HashMap.class);
        System.out.println("respMap = " + respMap);
        assertEquals(  ((Map)expDataMap.get("booking")).get("firstname")  , ((Map)respMap.get("booking")).get("firstname")  );
        assertEquals(  ((Map)expDataMap.get("booking")).get("lastname")  , ((Map)respMap.get("booking")).get("lastname")  );
        assertEquals(  ((Map)expDataMap.get("booking")).get("totalprice")  , ((Map)respMap.get("booking")).get("totalprice")  );
        assertEquals(  ((Map)expDataMap.get("booking")).get("depositpaid")  , ((Map)respMap.get("booking")).get("depositpaid")  );
        assertEquals(  ((Map)((Map)expDataMap.get("booking")).get("bookingdates")).get("checkin")    ,
                ((Map)((Map)respMap.get("booking")).get("bookingdates")).get("checkin")   );
        assertEquals(  ((Map)((Map)expDataMap.get("booking")).get("bookingdates")).get("checkout")    ,
                ((Map)((Map)respMap.get("booking")).get("bookingdates")).get("checkout")   );
        assertEquals(  ((Map)expDataMap.get("booking")).get("additionalneeds")  , ((Map)respMap.get("booking")).get("additionalneeds")  );

        /*
        {
       "bookingid":24,
       "booking":{
           "firstname":"Ali",
           "lastname":"Bak",
           "totalprice":500,
           "depositpaid":false,
           "bookingdates":{
               "checkin":"2021-06-01",
               "checkout":"2021-06-10"
                            },
           "additionalneeds":"wi-fi"
                 }
        }
        */
    }
}
