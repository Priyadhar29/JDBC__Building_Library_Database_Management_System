package com.libraryapp.DAO;

import java.util.List;
import com.libraryapp.model.Author;
import com.libraryapp.model.Book;

public interface AuthorDAO {
    
    List<Author> getAllAuthors();
    
    Author getAuthor(int id);
    
    void addAuthor(Author author);
    
    void updateAuthor(Author author);
    
    void deleteAuthor(int id);
    
}
