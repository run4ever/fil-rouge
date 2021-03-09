package fr.epita.filrouge.application.common;

import javax.validation.constraints.NotNull;

public class ViewingFrontSearchDto {

    @NotNull
    private String email;

    @NotNull
    private String title;

    private String page;

    public ViewingFrontSearchDto() {
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getPage() {
        return page;
    }
}
