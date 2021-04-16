import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Post201 extends BaseClass
{
    //Should be replaced with token based authentication
    @Test(description = "GitHub has deprecated basic credential auth in 2020")
    public void createRepoReturns201() throws IOException
    {
        //Create an HttpPost with a valid Endpoint
        HttpPost request = new HttpPost("https://api.github.com/repos/Mickey0001/repos");

        //Set Basic Auth Header
        String auth = "test@test.com" + "password123";
        //byte[] encodedAuth = Base64.getEncoder(StandardCharsets.ISO_8859_1));
        //String authHeader = "Basic" + new String(encodedAuth);

        //Define JSON to POST and set as Entity
        String json = "{\"name\": \"created\"}";
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        //Send the POST request
       // response = client.execute(request);

        //int actualStatusCode = response.getStatusLine().getStatusCode();
       // Assert.assertEquals(actualStatusCode, 201);
    }
}
