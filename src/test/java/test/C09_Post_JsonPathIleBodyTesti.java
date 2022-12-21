package test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class C09_Post_JsonPathIleBodyTesti {
    /*
        https://restful-booker.herokuapp.com/booking
         url’ine asagidaki body'ye sahip
        bir POST request gonderdigimizde
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
        donen Response’un,
        status code’unun 200,
        ve content type’inin application-json,
        ve response body’sindeki
            "firstname“in,"Ali",
            ve "lastname“in, "Bak",
            ve "totalprice“in,500,
            ve "depositpaid“in,false,
            ve "checkin" tarihinin 2021-06-01
            ve "checkout" tarihinin 2021-06-10
            ve "additionalneeds“in,"wi-fi"
        oldugunu test edin
     */

    @Test
    public void post01(){
        // 1 - Request URL ve body hazirla
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject innerBody = new JSONObject();
        JSONObject reqBody = new JSONObject();
        
        innerBody.put("checkin","2021-06-01");
        innerBody.put("checkout","2021-06-10");
        reqBody.put("firstname" , "Ali");
        reqBody.put("lastname" , "Bak");
        reqBody.put("totalprice" , 500);
        reqBody.put("depositpaid" ,false);
        reqBody.put("bookingdates" , innerBody);
        reqBody.put("additionalneeds" , "wi-fi");
        System.out.println("reqBody : "+ reqBody);

        // 2 - Expected Data hazirla
        // 3 - Response'u kaydet
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post(url);

        response.prettyPrint();

        // 4 - Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("booking.firstname", equalTo("Ali"),
                        "booking.lastname",equalTo("Bak"),
                        "booking.totalprice",equalTo(500),
                        "booking.depositpaid",equalTo(false),
                        "booking.bookingdates.checkin",equalTo("2021-06-01"),
                        "booking.bookingdates.checkout",equalTo("2021-06-10"),
                        "booking.additionalneeds",equalTo("wi-fi"));

        /*{
        "bookingid": 2225,
        "booking": {
                "firstname": "Ali",
                "lastname": "Bak",
                "totalprice": 500,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2021-06-01",
                    "checkout": "2021-06-10"
                },
        "additionalneeds": "wi-fi"
        }
        }*/

    }
    @Test
    public void post02(){
        /*
        https://restful-booker.herokuapp.com/booking
         url’ine asagidaki body'ye sahip
        bir POST request gonderdigimizde
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
        donen Response’un,
        status code’unun 200,
        ve content type’inin application-json,
        ve response body’sindeki
            "firstname“in,"Ali",
            ve "lastname“in, "Bak",
            ve "totalprice“in,500,
            ve "depositpaid“in,false,
            ve "checkin" tarihinin 2021-06-01
            ve "checkout" tarihinin 2021-06-10
            ve "additionalneeds“in,"wi-fi"
        oldugunu test edin
        */

        //1- Request icin URL ve Body hazirla
        String URL2="https://restful-booker.herokuapp.com/booking";
        JSONObject innerReqbookingdates=new JSONObject();
        innerReqbookingdates.put("checkin","2021-06-01");
        innerReqbookingdates.put("checkout","2021-06-10");

        JSONObject reqBody2=new JSONObject();
        reqBody2.put("firstname","Ali");
        reqBody2.put("lastname","Bak");
        reqBody2.put("totalprice",500);
        reqBody2.put("depositpaid",false);
        reqBody2.put("bookingdates",innerReqbookingdates);
        reqBody2.put("additionalneeds","wi-fi");

        System.out.println(reqBody2);

        //2- Expected Data yi hazirla
        //3- Response u hazirla
        Response response2=given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody2.toString())
                .post(URL2);
        response2.prettyPrint();

        //4- Assertion

        response2
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname",Matchers.equalTo("Ali"),
                    "booking.lastname",equalTo("Bak"),
                    "booking.totalprice",equalTo(500),
                    "booking.depositpaid",equalTo(false),
                    "booking.bookingdates.checkin",equalTo("2021-06-01"),
                    "booking.bookingdates.checkout",equalTo("2021-06-10"),
                        "booking.additionalneeds",equalTo("wi-fi"));

        /*{
            "bookingid": 2435,
                "booking": {
            "firstname": "Ali",
                    "lastname": "Bak",
                    "totalprice": 500,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2021-06-01",
                        "checkout": "2021-06-10"
            },
            "additionalneeds": "wi-fi"
        }
        }*/
    }
}
