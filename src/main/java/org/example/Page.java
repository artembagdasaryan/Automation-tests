package org.example;

public class Page {
    private final int id;
    private final String title;

    public Page(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "   Page{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
