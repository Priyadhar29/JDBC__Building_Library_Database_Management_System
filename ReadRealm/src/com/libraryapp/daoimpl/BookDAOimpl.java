package com.libraryapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libraryapp.DAO.BookDAO;
import com.libraryapp.model.Book;
import com.libraryapp.util.DBConnection;

public class BookDAOimpl implements BookDAO {
    
    private String INSERT_BOOK = "INSERT INTO books (bookId, title, authorId, publishedYear, genre, available, categoryId) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private String UPDATE_BOOK = "UPDATE books SET title = ?, authorId = ?, publishedYear = ?, genre = ?, available = ?, categoryId = ? WHERE bookId = ?";
    private String SELECT_BOOK = "SELECT * FROM books WHERE bookId = ?";
    private String DELETE_BOOK = "DELETE FROM books WHERE bookId = ?";
    private String SELECT_ALL_BOOKS = "SELECT * FROM books";

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {
            
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                int id = res.getInt("bookId");
                String title = res.getString("title");
                int authorId = res.getInt("authorId");
                int publishedYear = res.getInt("publishedYear");
                String genre = res.getString("genre");
                boolean available = res.getBoolean("available");
                int categoryId = res.getInt("categoryId");
                books.add(new Book(id, title, authorId, publishedYear, genre, available, categoryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBook(int bookId) {
        Book book = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK)) {
            
            preparedStatement.setInt(1, bookId);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                int id = res.getInt("bookId");
                String title = res.getString("title");
                int authorId = res.getInt("authorId");
                int publishedYear = res.getInt("publishedYear");
                String genre = res.getString("genre");
                boolean available = res.getBoolean("available");
                int categoryId = res.getInt("categoryId");
                book = new Book(id, title, authorId, publishedYear, genre, available, categoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void addBook(Book book) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK)) {
            
            preparedStatement.setInt(1, book.getBookId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getAuthorId());
            preparedStatement.setInt(4, book.getPublishedYear());
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.setBoolean(6, book.isAvailable());
            preparedStatement.setInt(7, book.getCategoryId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK)) {
            
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.setInt(3, book.getPublishedYear());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setBoolean(5, book.isAvailable());
            preparedStatement.setInt(6, book.getCategoryId());
            preparedStatement.setInt(7, book.getBookId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int bookId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
            
            preparedStatement.setInt(1, bookId);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Deleted rows: " + res);
            } else {
                System.out.println("No book found with id: " + bookId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
