package fr.epita.filrouge.infrastructure.http;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ExternalApiClientConfig {

    private static final String BASE_URL = "https://www.omdbapi.com";

    /**
     * Configure a RestTemplate with errorHandler to call external Api
     *
     * @param restTemplateBuilder injected By Spring
     *
     * @return a RestTemplate
     */
    @Bean
    public RestTemplate getRestClient(final RestTemplateBuilder restTemplateBuilder) {

        final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        return restTemplateBuilder.interceptors(interceptors) //
                .rootUri(BASE_URL) //
                .build();
    }



}
