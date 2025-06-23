package com.libraryapp.model;

public class Book {
    private int bookId;
    private String title;
    private int authorId;
    private int publishedYear;
    private String genre;
    private boolean available;
    private int categoryId;

    public Book() {
    }

    public Book(int bookId, String title, int authorId, int publishedYear, String genre, boolean available, int categoryId) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.available = available;
        this.categoryId = categoryId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", authorId=" + authorId + ", publishedYear=" + publishedYear
                + ", genre=" + genre + ", available=" + available + ", categoryId=" + categoryId + "]";
    }
}
