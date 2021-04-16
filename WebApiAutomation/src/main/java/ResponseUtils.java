import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.NotFound;
import entities.User;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils
{
    public static String getHeader(CloseableHttpResponse response, String headerName)
    {
        //Get all headers
        Header[] headers = response.getAllHeaders();
        Header[] httpHeaders = headers;
        String returnHeader = "";

        //Loop over the headers list
        for (Header header : httpHeaders)
        {
            if (headerName.equalsIgnoreCase(header.getName()))
            {
                returnHeader = header.getValue();
            }
        }

        //Throw an exception if no header found
        if (returnHeader.isEmpty())
        {
            throw new RuntimeException("The following header can't be found: " + headerName);
        }

        //Return the header
        return  returnHeader;
    }

    public  static String getHeaderJava8Way(CloseableHttpResponse response, String headerName)
    {
        List<Header> httpHeaders =  Arrays.asList(response.getAllHeaders());

        Header matchHeader = httpHeaders.stream()
                                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                                .findFirst().orElseThrow(() -> new RuntimeException("Header not found."));
        return  matchHeader.getValue();
    }

    public  static boolean headerIsPresent(CloseableHttpResponse response, String headerName)
    {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());

        return  httpHeaders.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }

    public static User unmarshall(CloseableHttpResponse response, Class<User> clazz) throws IOException
    {
        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, clazz);
    }

    public static <T> T unmarshallGeneric(CloseableHttpResponse response, Class<T> clazz) throws IOException
    {
        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, clazz);
    }
}
