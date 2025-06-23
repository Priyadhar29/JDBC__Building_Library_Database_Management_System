package com.libraryapp.DAO;

import java.util.List;
import com.libraryapp.model.Member;

public interface CustomerDAO {
    
    List<Member> getAllCustomers();
    
    Member getCustomer(int memberId);
    
    void addCustomer(Member members);
    
    void updateCustomer(Member members);
    
    void deleteCustomer(int memberId);
   
}