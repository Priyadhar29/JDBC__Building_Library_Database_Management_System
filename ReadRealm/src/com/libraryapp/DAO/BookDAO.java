package com.libraryapp.DAO;

import java.util.List;
import com.libraryapp.model.Book;

public interface BookDAO {
    
    List<Book> getAllBooks();
    
    Book getBook(int id);
    
    void addBook(Book book);
    
    void updateBook(Book book);
    
    void deleteBook(int id);

}