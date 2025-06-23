package com.libraryapp.model;

public class Author {
    private int authorId;
    private String name;
    private String bio;

    public Author() {
    }

    public Author(int authorId, String name, String bio) {
        this.authorId = authorId;
        this.name = name;
        this.bio = bio;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Author [authorId=" + authorId + ", name=" + name + ", bio=" + bio + "]";
    }
}
