package org.example;

import java.util.List;


public class GreenAllEvents {
    private final List<Page> page;
    private final int totalElements;
    private final int currentPage;
    private final int totalPages;
    private final int number;
    private final boolean hasPrevious;
    private final boolean hasNext;
    private final boolean first;
    private final boolean last;

    public GreenAllEvents(List<Page> page, int totalElements, int currentPage,
                          int totalPages, int number, boolean hasPrevious,
                          boolean hasNext, boolean first, boolean last) {
        this.page = page;
        this.totalElements = totalElements;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.number = number;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
        this.first = first;
        this.last = last;
    }

    @Override
    public String toString() {
        return "GreencityAllEvents{" +
                "page=" + page +
                ", \ntotalElements=" + totalElements +
                ", currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", number=" + number +
                ", hasPrevious=" + hasPrevious +
                ", hasNext=" + hasNext +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}