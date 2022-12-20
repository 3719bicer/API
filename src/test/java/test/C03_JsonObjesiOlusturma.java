package test;

import org.json.JSONObject;
import org.junit.Test;

public class C03_JsonObjesiOlusturma {

    /*
    Asagidaki JSON objesini olusturup konsola yazdırın.

    {"title":"Ahmet","body":"Merhaba","userId":"1"}

    {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":1
    }
    */

    @Test
    public void jsonObje(){

        JSONObject ilkJSONObje= new JSONObject();

        ilkJSONObje.put("title","Ahmet");
        ilkJSONObje.put("body","Merhaba");
        ilkJSONObje.put("userId","1");

        System.out.println(ilkJSONObje);
    }

    @Test
    public void jsonObje2(){
        /*
            {
                "firstname":"Jim",
                "additionalneeds":"Breakfast",
                "bookingdates":{
                    "checkin":"2018-01-01",
                    "checkout":"2019-01-01"
                    },
                "totalprice":111,
                "depositpaid":true,
                "lastname":"Brown"
            }
        */

        JSONObject innerJson= new JSONObject();
        innerJson.put("checkin","2018-01-01");
        innerJson.put("checkout","2019-01-01");

        JSONObject body= new JSONObject();
        body.put("firstname","Jim");
        body.put("additionalneeds","Breakfast");
        body.put("totalprice",111);
        body.put("depositpaid",true);
        body.put("lastname","Brown");
        body.put("bookingdates",innerJson);

        System.out.println(body);

    }

    /*
    @Test
    public void jsonObject01(){
        JSONObject jsonObject01=new JSONObject();
        JSONObject innerJSON=new JSONObject();

        innerJSON.put("checkin", "2018-01-01");
        innerJSON.put("checkout", "2019-01-01");

        jsonObject01.put("firstname","John");
        jsonObject01.put("lastname","Smith");
        jsonObject01.put("totalprice","111");
        jsonObject01.put("depositpaid",true);
        jsonObject01.put("bookingdates",innerJSON);
        jsonObject01.put("additionalneeds","Dinner");

        System.out.println("jsonObject01 : "+jsonObject01);

    }
    */
}
