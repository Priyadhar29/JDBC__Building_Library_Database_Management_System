package com.libraryapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libraryapp.DAO.AuthorDAO;
import com.libraryapp.model.Author;
import com.libraryapp.util.DBConnection;

public class AuthorDAOimpl implements AuthorDAO {
    
    private String INSERT_AUTHOR = "INSERT INTO authors (author_id, name, bio) VALUES (?, ?, ?)";
    private String UPDATE_AUTHOR = "UPDATE authors SET name = ?, bio = ? WHERE author_id = ?";
    private String SELECT_AUTHOR = "SELECT * FROM authors WHERE author_id = ?";
    private String DELETE_AUTHOR = "DELETE FROM authors WHERE author_id = ?";
    private String SELECT_ALL_AUTHORS = "SELECT * FROM authors";

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUTHORS)) {
            
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                int id = res.getInt("author_id");
                String name = res.getString("name");
                String bio = res.getString("bio");
                authors.add(new Author(id, name, bio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author getAuthor(int authorId) {
        Author author = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHOR)) {
            
            preparedStatement.setInt(1, authorId);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                int id = res.getInt("author_id");
                String name = res.getString("name");
                String bio = res.getString("bio");
                author = new Author(id, name, bio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public void addAuthor(Author author) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHOR)) {
            
            preparedStatement.setInt(1, author.getAuthorId());
            preparedStatement.setString(2, author.getName());
            preparedStatement.setString(3, author.getBio());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAuthor(Author author) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR)) {
            
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getBio());
            preparedStatement.setInt(3, author.getAuthorId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAuthor(int authorId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHOR)) {
            
            preparedStatement.setInt(1, authorId);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Deleted rows: " + res);
            } else {
                System.out.println("No author found with id: " + authorId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
