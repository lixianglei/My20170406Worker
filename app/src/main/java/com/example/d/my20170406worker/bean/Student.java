package com.example.d.my20170406worker.bean;

/**
 * Created by d on 2017/4/5.
 */

public class Student {
    private String title;
    private String description;
    private String author;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String body;
    private String url;
    private String id;
    public Student() {
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student(String title, String body, String url, String id) {
        this.title = title;
        this.body = body;
        this.url = url;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
