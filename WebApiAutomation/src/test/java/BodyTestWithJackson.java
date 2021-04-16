import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.NotFound;
import entities.RateLimit;
import entities.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class BodyTestWithJackson extends BaseClass
{
    @Test
    public void returnsCorrectLogin() throws IOException
    {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/Mickey0001");

        response = client.execute(get);

        User user = ResponseUtils.unmarshallGeneric(response, User.class);

        Assert.assertEquals(user.getLogin(), "Mickey0001");
    }

    @Test
    public void returnsCorrectID() throws IOException
    {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/Mickey0001");

        response = client.execute(get);

        User user = ResponseUtils.unmarshallGeneric(response, User.class);

        Assert.assertEquals(user.getId(), 23509920);
    }


    @Test
    public void notFoundMessageIsCorrect() throws IOException
    {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/fakeAssEndPoint");

        response = client.execute(get);

        NotFound notFoundMessage = ResponseUtils.unmarshallGeneric(response, NotFound.class);

        Assert.assertEquals(notFoundMessage.getMessage(), "Not Found");
    }

    @Test
    public void correctRateLimitIsSet() throws IOException
    {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");

        response = client.execute(get);

        RateLimit rateLimits = ResponseUtils.unmarshallGeneric(response, RateLimit.class);

        assertEquals(rateLimits.getCoreLimit(), 60);
        assertEquals(rateLimits.getSearchLimit(), "10");
    }

}
