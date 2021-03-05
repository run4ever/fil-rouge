package fr.epita.filrouge.domain.exception;

import org.springframework.web.client.HttpStatusCodeException;

public class ExternalApiTechnicalException extends TechnicalException {
    public ExternalApiTechnicalException(final String message, final HttpStatusCodeException e) {
        super(message, e);
    }


}
