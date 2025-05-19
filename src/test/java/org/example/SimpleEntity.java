package org.example;

class SimpleEntity {

    private final String content;

    public SimpleEntity(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "SimpleEntity [content=" + content + "]";
    }
}
