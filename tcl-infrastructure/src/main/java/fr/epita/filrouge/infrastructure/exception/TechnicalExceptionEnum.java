package fr.epita.filrouge.infrastructure.exception;

/**
 * Liste des exceptions de la couche infrastructure.
 * JPA  peut remonter 4 types d'exceptions différentes toutes issues de DataAccessException.
 * Ici nous nous intéressons à l'intention d'accès à l'origine de l'exception.
 * @Author : Yoss
 */
public enum TechnicalExceptionEnum {

    JPA_READ_ACCESS("JPA", "Accès en lecture"),
    JPA_CREATE_ACCESS("JPA", "Accès en insertion"),
    JPA_UPDATE_ACCESS("JPA", "Accès en mise à jour"),
    JPA_DELETE_ACCESS("JPA", "Accès en suppression")
    ;
    private String exceptionType;
    private String exceptionOrigin;

    TechnicalExceptionEnum(String exceptionType, String exceptionOrigin) {
        this.exceptionType = exceptionType;
        this.exceptionOrigin = exceptionOrigin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder ("TechnicalExceptionEnum{");
        sb.append ("exceptionType='").append (exceptionType).append ('\'');
        sb.append (", exceptionOrigin='").append (exceptionOrigin).append ('\'');
        sb.append ('}');
        return sb.toString ();
    }
}
