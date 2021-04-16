import org.apache.http.client.methods.HttpOptions;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class Options204 extends BaseClass
{
    @Test
    public  void optionsReturnsCorrectMethodList() throws IOException
    {
        String header = "Access-Control-Allow-Methods";
        String expectReply = "GET, POST, PATCH, PUT, DELETE";

        HttpOptions request = new HttpOptions(BASE_ENDPOINT);
        response = client.execute(request);

        String actualValue = ResponseUtils.getHeader(response, header);

        assertEquals(actualValue, expectReply);
    }
}
