package com.example.RunToU;

public class Dataitem {
    public int id;
    public int chatroomPK;
    public String content;
    public String name;
    public int viewType;

    public Dataitem(String content, String name, int viewType) {
        this.content=content;
        this.viewType=viewType;
        this.name=name;
    }

    public String getContent() {return content;}
    public String getName() {return name;}
    public int getViewType() { return viewType;}
}
