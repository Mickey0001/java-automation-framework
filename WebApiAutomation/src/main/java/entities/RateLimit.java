package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RateLimit
{
    private int coreLimit;

    private String searchLimit;

    public  int getCoreLimit()
    {
        return coreLimit;
    }

    public String getSearchLimit()
    {
        return searchLimit;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("resources")
    private void unmarshallNested(Map<String, Object> resource)
    {
        Map<String, Integer> core = (Map<String, Integer>) resource.get("core");
        coreLimit = core.get("limit");

        Map<String, String> search = (Map<String, String>) resource.get("search");
        searchLimit = String.valueOf(search.get("limit"));
    }
}
