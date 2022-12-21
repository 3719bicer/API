package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C08_JSONObjectVerileriCagirma {
        /*
        {
        "firstName": "John",
        "lastName": "doe",
        "address": {
            "streetAddress": "naist street",
            "city": "Nara",
            "postalCode": "630-0192"
                    },
        "age": 26,
        "phoneNumbers": [
            {
                "type": "iPhone",
                "number": "0123-4567-8888"
            },
            {
                "type": "home",
                "number": "0123-4567-8910"
            }
        ]
        }
        */

    @Test
    public void jsonPath01(){
        JSONObject kisiBilgisi = new JSONObject();
        JSONObject adresBilgisi = new JSONObject();
        JSONObject cepTelBilgisi = new JSONObject();
        JSONObject evTelBilgisi = new JSONObject();
        JSONArray telBilgileri = new JSONArray();

        cepTelBilgisi.put("type","iPhone");
        cepTelBilgisi.put("number","0123-4567-8888");

        evTelBilgisi.put("type","home");
        evTelBilgisi.put("number","0123-4567-8910");

        telBilgileri.put(cepTelBilgisi);
        telBilgileri.put(evTelBilgisi);

        adresBilgisi.put("streetAddress","naist street");
        adresBilgisi.put("city","Nara");
        adresBilgisi.put("postalCode","630-0192");

        kisiBilgisi.put("firstName","John");
        kisiBilgisi.put("lastName","doe");
        kisiBilgisi.put("age",26);
        kisiBilgisi.put("address",adresBilgisi);
        kisiBilgisi.put("phoneNumbers",telBilgileri);

        System.out.println(kisiBilgisi);
        System.out.println("Isim : " + kisiBilgisi.get("firstName"));
        System.out.println("Soyisim : " + kisiBilgisi.get("lastName"));
        System.out.println("Yas : " + kisiBilgisi.get("age"));
        System.out.println("Sehir : " + kisiBilgisi.getJSONObject("address").get("city"));
        System.out.println("Posta Kodu : " + kisiBilgisi.getJSONObject("address").get("postalCode"));
        System.out.println("Sokak adi : " + kisiBilgisi.getJSONObject("address").get("streetAddress"));

        System.out.println("Cep Tel : " + kisiBilgisi.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));

        System.out.println("Telefon Type : " + kisiBilgisi.getJSONArray("phoneNumbers").getJSONObject(1).get("type"));

        //Burada ise var olan JSON lar bazında catıyı kuracagız.
        //Kac adet JSON var dedigimizde acılıp kapanan süslü parantezleri esas alacagız.
        //4 adet var +1 de ArrayJSON var.

    }
    @Test
    public void jsonPath02(){
        /*
        {
        "firstName":"John",
        "lastName":"doe",
        "address":
                    {
                    "streetAddress":"naist street",
                    "city":"Nara",
                    "postalCode":"630-0192"
                    },
        "age":26,
        "phoneNumbers":
                    [
                        {
                            "number":"0123-4567-8888",
                            "type":"iPhone"
                        },
                        {
                            "number":"0123-4567-8910",
                            "type":"home"
                        }
                    ]
        }
        */

        JSONObject personalInfo=new JSONObject();
        JSONObject addressInfo=new JSONObject();
        JSONArray phoneInfo=new JSONArray();
        JSONObject mobilephoneInfo=new JSONObject();
        JSONObject homephoneInfo=new JSONObject();

        mobilephoneInfo.put("type","iPhone");
        mobilephoneInfo.put("number","0123-4567-8888");

        homephoneInfo.put("type","home");
        homephoneInfo.put("number","0123-4567-8910");

        phoneInfo.put(mobilephoneInfo);
        phoneInfo.put(homephoneInfo);

        addressInfo.put("streetAddress","naist street");
        addressInfo.put("city","Nara");
        addressInfo.put("postalCode","630-0192");

        personalInfo.put("firstName","John");
        personalInfo.put("lastName","doe");
        personalInfo.put("address",addressInfo);
        personalInfo.put("age",26);
        personalInfo.put("phoneNumbers",phoneInfo);

        System.out.println("PersonalInfo : " + personalInfo);

        System.out.println("FirstName :" + personalInfo.get("firstName"));
        System.out.println("LastName :" + personalInfo.get("lastName"));
        System.out.println("StreetAddress :" + personalInfo.getJSONObject("address").get("streetAddress"));
        System.out.println("City : " + personalInfo.getJSONObject("address").get("city"));
        System.out.println("PostalCode : " + personalInfo.getJSONObject("address").get("postalCode"));
        System.out.println("Age : " + personalInfo.get("age"));

        System.out.println("Mobilephone Type : " + personalInfo.getJSONArray("phoneNumbers").getJSONObject(0).get("type"));
        System.out.println("Mobilephone Number: " + personalInfo.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));

        System.out.println("Homephone Type : " + personalInfo.getJSONArray("phoneNumbers").getJSONObject(1).get("type"));
        System.out.println("Homephone Number : " + personalInfo.getJSONArray("phoneNumbers").getJSONObject(1).get("number"));

    }
}
