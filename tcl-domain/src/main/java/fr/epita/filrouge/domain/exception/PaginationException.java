package fr.epita.filrouge.domain.exception;

public class PaginationException extends BusinessException {
    public PaginationException(String message, String errorCode) {
        super(message, errorCode);
    }
}
