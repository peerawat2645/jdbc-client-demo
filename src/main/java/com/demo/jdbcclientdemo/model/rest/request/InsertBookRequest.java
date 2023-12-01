package com.demo.jdbcclientdemo.model.rest.request;

public class InsertBookRequest {
    private String name;
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{");
        sb.append("name=").append(name);
        sb.append(", title=").append(title);
        sb.append('}');
        return sb.toString();
    }
}
