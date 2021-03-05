package fr.epita.filrouge.infrastructure.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieSearchInfo {

    // conversion tool : https://json2csharp.com/json-to-pojo

    @JsonProperty("Search")
    public List<MovieInfo> search;
    public String totalResults;
    @JsonProperty("Response")
    public String response;

    public List<MovieInfo> getSearch() {
        return search;
    }

    public void setSearch(List<MovieInfo> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
