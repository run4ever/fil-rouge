package fr.epita.filrouge.infrastructure.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SerieSearchInfo {

    // conversion tool : https://json2csharp.com/json-to-pojo

    @JsonProperty("Search")
    public List<SerieInfo> search;
    public String totalResults;
    @JsonProperty("Response")
    public String response;

    public List<SerieInfo> getSearch() {
        return search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }
}
