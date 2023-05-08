package com.bloomtech.library.models.checkableTypes;

public class Media extends Checkable {
    private MediaType mediaType;
    private String author;

    public Media() {
    }

    public Media(String isbn, String title, String author, MediaType mediaType) {
        super(isbn, title);
        this.author = author;
        this.mediaType = mediaType;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
