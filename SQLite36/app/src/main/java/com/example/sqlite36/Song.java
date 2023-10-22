package com.example.sqlite36;

public class Song {
    private String title;
    private String author;
    private String youtubeUrl;

    public Song(String title, String author, String youtubeUrl) {
        this.title = title;
        this.author = author;
        this.youtubeUrl = youtubeUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }
}
