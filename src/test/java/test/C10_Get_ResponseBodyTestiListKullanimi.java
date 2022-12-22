package test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class C10_Get_ResponseBodyTestiListKullanimi {
        /*
            http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
            donen Response'in;

            status code'unun 200,
            ve content type'inin Aplication.JSON,
            ve response body'sindeki
                employees sayisinin 24
                ve employee'lerden birinin "Ashton Cox"
                ve girilen yaslar icinde 61,40 ve 30 degerlerinin oldugunu
            test edin.
         */
    @Test
    public void get01(){
        // 1 - Request URL hazirla
        String url = "http://dummy.restapiexample.com/api/v1/employees";

        // 2 - Expected Data
        // 3 - Response'u kaydet
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4 - Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id",hasSize(24),
                 "data.employee_name",hasItem("Ashton Cox"),
                        "data.employee_age",hasItems(61,40,30));
    }

    @Test
    public void get02(){
        /*
            http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
            donen Response'in;

            status code'unun 200,
            ve content type'inin Aplication.JSON,
            ve response body'sindeki
                employees sayisinin 24
                ve employee'lerden birinin "Ashton Cox"
                ve girilen yaslar icinde 61,40 ve 30 degerlerinin oldugunu
            test edin.
         */
        String URL2="http://dummy.restapiexample.com/api/v1/employees";
        Response response=given().contentType(ContentType.JSON).when().get(URL2);
        response.prettyPrint(); //Jsonpath.com a yapistirdik.

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id",hasSize(24),
                        "data.employee_name",hasItem("Ashton Cox"),
                        "data.employee_age",hasItems(61,40,30));
    }
}
