package com.example.RunToU;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dataitem {
    public int id;
    public int chatroomPK;
    public String content;
    public String name;
    public int viewType;
    public String date;
    @RequiresApi(Build.VERSION_CODES.O)
    public String datetime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        date = localDateTime.format(formater);
        return date;
    }
    public Dataitem(String content, String name, int viewType) {
        this.content=content;
        this.viewType=viewType;
        this.name=name;
    }

    public String getContent() {return content;}
    public String getName() {return name;}
    public int getViewType() { return viewType;}

}
