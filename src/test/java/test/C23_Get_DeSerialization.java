package test;
import baseURL.DummyBaseURL;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
public class C23_Get_DeSerialization extends DummyBaseURL {

     /*
     http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET
     request gonderdigimizde donen response’un
     status code’unun 200,
     content Type’inin application/json ve
     body’sinin asagidaki gibi
     oldugunu test edin.

     Response Body
     {
     "status":"success",
     "data":{
         "id":3,
         "employee_name":"Ashton Cox",
         "employee_salary":86000,
         "employee_age":66,
         "profile_image":""
         },
     "message":"Successfully! Record has been fetched."
     }
     */

    @Test
    public void get01(){
        // 1 - Request icin Url ve Body hazirla
        specDummy.pathParams("pp1","employee","pp2",3);

        // 2 - Expected Data hazirla
        TestDataDummy testDataDummy = new TestDataDummy();
        HashMap <String,Object> expDataMap = testDataDummy.expectedDataOlusturMap();
        System.out.println("expDataMap = " + expDataMap);

        // 3 - Response'u kaydet
        Response response = given().spec(specDummy).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4 - Assertion

        // Not : Bizim hazirlamis oldugumuz Expected Data Map formatinda.
        // Bize response'dan donen Response Body ise Json formatinda
        // Ikisini Assert methodlari icerisinde kiyaslayabilmemiz icin oncelikle
        // response'i map formatina parse etmemiz gerekiyor.

        HashMap<String,Object> respMap = response.as(HashMap.class);
        System.out.println("respMap = " + respMap);

        assertEquals(testDataDummy.statusCode,response.getStatusCode());
        assertEquals(testDataDummy.contentType,response.getContentType());
        assertEquals(expDataMap.get("status"),respMap.get("status"));
        assertEquals(((Map)expDataMap.get("data")).get("id"),((Map)respMap.get("data")).get("id")     );
        assertEquals(((Map)expDataMap.get("data")).get("employee_name"),((Map)respMap.get("data")).get("employee_name")      );
        assertEquals(((Map)expDataMap.get("data")).get("employee_salary"),((Map)respMap.get("data")).get("employee_salary")     );
        assertEquals(((Map)expDataMap.get("data")).get("employee_age") ,((Map)respMap.get("data")).get("employee_age")   );
        assertEquals(((Map)expDataMap.get("data")).get("profile_image"),((Map)respMap.get("data")).get("profile_image")   );
        assertEquals(expDataMap.get("message"),respMap.get("message"));

        /*
        {
        "status":"success",
        "data":{
                "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                },
        "message":"Successfully! Record has been fetched."
        }
        */
    }
}
