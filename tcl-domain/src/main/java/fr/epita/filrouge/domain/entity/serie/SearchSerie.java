package fr.epita.filrouge.domain.entity.serie;

import fr.epita.filrouge.domain.entity.common.Category;

public class SearchSerie {
    private String imdbId;
    private String title;
    private String description;
    private Integer startYearLowerBound;
    private Integer startYearUpperBound;
    private String status;
    private String category;
    private int offset;
    private int limit;
    private String sortAttribute;
    private boolean sortAsc;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartYearLowerBound() {
        return startYearLowerBound;
    }

    public void setStartYearLowerBound(Integer startYearLowerBound) {
        this.startYearLowerBound = startYearLowerBound;
    }

    public Integer getStartYearUpperBound() {
        return startYearUpperBound;
    }

    public void setStartYearUpperBound(Integer startYearUpperBound) {
        this.startYearUpperBound = startYearUpperBound;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSortAttribute() {
        return sortAttribute;
    }

    public void setSortAttribute(String sortAttribute) {
        this.sortAttribute = sortAttribute;
    }

    public boolean isSortAsc() {
        return sortAsc;
    }

    public void setSortAsc(boolean sortAsc) {
        this.sortAsc = sortAsc;
    }
}
