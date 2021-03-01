package fr.epita.filrouge.application.common;

import java.util.List;

/**
 * @Author : Yoss
 * Page restituée à l'IHM
 * Permet de s'adapter à tout type d'objet : vidéo, série, visionnage
 */
public class PageDTO {

    private List<? extends Object> listViewingOrSerieOrVideo;
    private long total;
    private int offset;
    private int limit;
    private String sortAttribute;
    boolean sortAsc;

    public List<? extends Object> getListViewingOrSerieOrVideo() {
        return listViewingOrSerieOrVideo;
    }

    public void setListViewingOrSerieOrVideo(List<? extends Object> listViewingOrSerieOrVideo) {
        this.listViewingOrSerieOrVideo = listViewingOrSerieOrVideo;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
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
