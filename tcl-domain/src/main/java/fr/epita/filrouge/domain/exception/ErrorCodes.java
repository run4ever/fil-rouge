package fr.epita.filrouge.domain.exception;

public class ErrorCodes {




    public ErrorCodes() {}
    public static final String USER_NOT_FOUND = "ERR_0001";
    public static final String MOVIE_NOT_FOUND = "ERR_0002";
    public static final String SERIE_NOT_FOUND = "ERR_0003";
  
    public static final String USER_ALREADY_EXISTING = "ERR_0010";
    public static final String MOVIE_ALREADY_EXISTING = "ERR_0011";
    public static final String MOVIE_ALREADY_EXISTING_IN_VIEWINGMOVIE = "ERR_0012";
    public static final String SERIE_ALREADY_EXISTING = "ERR_0014";
    public static final String VIEWVING_SERIE_ALREADY_EXISTING = "ERR_0015";
    public static final String VIEWING_SERIE_ALREADY_EXISTING = "ERR_0016";


}
