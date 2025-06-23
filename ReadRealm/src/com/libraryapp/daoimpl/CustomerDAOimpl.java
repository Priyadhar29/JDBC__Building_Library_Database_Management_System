package com.libraryapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.libraryapp.DAO.CustomerDAO;
import com.libraryapp.model.Member;
import com.libraryapp.util.DBConnection;

public class CustomerDAOimpl implements CustomerDAO {
    
    private String INSERT_MEMBER = "INSERT INTO members (memberId, name, email, phone, joinDate, membershipType) VALUES (?, ?, ?, ?, ?, ?)";
    private String UPDATE_MEMBER = "UPDATE members SET name = ?, email = ?, phone = ?, joinDate = ?, membershipType = ? WHERE memberId = ?";
    private String SELECT_MEMBER = "SELECT * FROM members WHERE memberId = ?";
    private String DELETE_MEMBER = "DELETE FROM members WHERE memberId = ?";
    private String SELECT_ALL_MEMBERS = "SELECT * FROM members";

    @Override
    public List<Member> getAllCustomers() {
        List<Member> members = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEMBERS)) {
            
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                int id = res.getInt("memberId");
                String name = res.getString("name");
                String email = res.getString("email");
                String phone = res.getString("phone");
                Date joinDate = res.getDate("joinDate");
                String membershipType = res.getString("membershipType");
                members.add(new Member(id, name, email, phone, joinDate, membershipType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Member getCustomer(int memberId) {
        Member members = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEMBER)) {
            
            preparedStatement.setInt(1, memberId);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                int id = res.getInt("memberId");
                String name = res.getString("name");
                String email = res.getString("email");
                String phone = res.getString("phone");
                Date joinDate = res.getDate("joinDate");
                String membershipType = res.getString("membershipType");
                members = new Member(id, name, email, phone, joinDate, membershipType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public void addCustomer(Member members) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEMBER)) {
            
            preparedStatement.setInt(1, members.getMemberId());
            preparedStatement.setString(2, members.getName());
            preparedStatement.setString(3, members.getEmail());
            preparedStatement.setString(4, members.getPhone());
            preparedStatement.setDate(5, members.getJoinDate());
            preparedStatement.setString(6, members.getMembershipType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Member members) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEMBER)) {
            
            preparedStatement.setString(1, members.getName());
            preparedStatement.setString(2, members.getEmail());
            preparedStatement.setString(3, members.getPhone());
            preparedStatement.setDate(4, members.getJoinDate());
            preparedStatement.setString(5, members.getMembershipType());
            preparedStatement.setInt(6, members.getMemberId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int memberId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEMBER)) {
            
            preparedStatement.setInt(1, memberId);
            int res = preparedStatement.executeUpdate();
            if (res > 0) {
                System.out.println("Deleted rows: " + res);
            } else {
                System.out.println("No member found with id: " + memberId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
