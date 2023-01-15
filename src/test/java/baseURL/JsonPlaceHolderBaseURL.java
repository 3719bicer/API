package baseURL;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
public class JsonPlaceHolderBaseURL {

    public static Response response;
    public static String URL;

    protected RequestSpecification specJsonPlace;

    @Before
    public void setUp(){
        specJsonPlace = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
}
