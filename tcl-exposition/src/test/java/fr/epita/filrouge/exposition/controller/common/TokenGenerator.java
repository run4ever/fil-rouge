package fr.epita.filrouge.exposition.controller.common;

import org.springframework.http.HttpHeaders;

public interface TokenGenerator {
    HttpHeaders getHeadersWithJwtToken(String email);
}
