package com.libraryapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libraryapp.DAO.CategoryDAO;
import com.libraryapp.model.Category;
import com.libraryapp.util.DBConnection;

public class CategoryDAOimpl implements CategoryDAO {
    
    private String INSERT_CATEGORY = "INSERT INTO categories (categoryId, categoryName) VALUES (?, ?)";
    private String UPDATE_CATEGORY = "UPDATE categories SET categoryName = ? WHERE categoryId = ?";
    private String SELECT_CATEGORY = "SELECT * FROM categories WHERE categoryId = ?";
    private String DELETE_CATEGORY = "DELETE FROM categories WHERE categoryId = ?";
    private String SELECT_ALL_CATEGORIES = "SELECT * FROM categories";

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES)) {
            
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                int id = res.getInt("categoryId");
                String name = res.getString("categoryName");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category getCategory(int categoryId) {
        Category category = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY)) {
            
            preparedStatement.setInt(1, categoryId);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                int id = res.getInt("categoryId");
                String name = res.getString("categoryName");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public void addCategory(Category category) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY)) {
            
            preparedStatement.setInt(1, category.getCategoryId());
            preparedStatement.setString(2, category.getCategoryName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(Category category) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {
            
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setInt(2, category.getCategoryId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(int categoryId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY)) {
            
            preparedStatement.setInt(1, categoryId);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Deleted rows: " + res);
            } else {
                System.out.println("No category found with id: " + categoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
