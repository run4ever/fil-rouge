package fr.epita.filrouge.infrastructure.exception;

/**
 * Classe pour centraliser les exceptions techniques
 * @Author : Yoss
 */
public class TechnicalException extends RuntimeException {

    private TechnicalExceptionEnum technicalExceptionEnum;
    public TechnicalException(TechnicalExceptionEnum technicalExceptionEnum, String message) {
        super(message);
        this.technicalExceptionEnum = technicalExceptionEnum;
    }

    public TechnicalExceptionEnum getExceptionMessage() {
        return technicalExceptionEnum;
    }
}
