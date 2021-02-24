package fr.epita.filrouge.domain.exception;

public class AlreadyExistingException extends BusinessException {
    public AlreadyExistingException(String message, String errorCode) {
        super(message, errorCode);
    }
}
