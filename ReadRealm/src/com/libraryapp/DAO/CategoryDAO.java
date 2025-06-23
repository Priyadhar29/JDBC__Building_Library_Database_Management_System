package com.libraryapp.DAO;

import java.util.List;

import com.libraryapp.model.Book;
import com.libraryapp.model.Category;

public interface CategoryDAO {
    
    List<Category> getAllCategories();
    
    Category getCategory(int id);
    
    void addCategory(Category category);
    
    void updateCategory(Category category);
    
    void deleteCategory(int id);
    
}