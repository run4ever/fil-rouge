package fr.epita.filrouge.domain.exception;

import org.springframework.web.client.HttpStatusCodeException;

public class TechnicalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TechnicalException(final String msg, final HttpStatusCodeException e) {
        super(msg, e);
    }

}