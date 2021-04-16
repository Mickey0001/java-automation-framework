import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class DeleteReturns403 extends BaseClass
{
    public static String TOKEN = "968cafc68dae379f40a9bebe6e0cb1c6300c5943";
    @Test
    public void DeleteIsNotSuccessful() throws IOException
    {
        HttpDelete request = new HttpDelete("https://api.github.com/repos/Mickey0001/TestDelete");

        request.setHeader(HttpHeaders.AUTHORIZATION, "token" + TOKEN);
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();

        assertEquals(actualStatusCode, 403);
    }
}
