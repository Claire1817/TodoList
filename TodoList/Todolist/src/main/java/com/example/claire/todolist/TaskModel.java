package com.example.claire.todolist;

/**
 * Created by claire on 11/01/2017.
 */

public class TaskModel {

    private long id;
    private String name;
    private boolean check;

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String content;

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    private String date;

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}
