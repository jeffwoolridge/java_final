// File: src/main/java/com/gym/dao/GymMerchDAO.java
package com.app.dao;

import com.app.model.GymMerch;
import com.app.util.DatabaseConnection;
import com.app.util.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymMerchDAO {
    private Connection connection;

    public GymMerchDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean createMerch(GymMerch merch) {
        String sql = "INSERT INTO gym_merch (merch_name, merch_type, merch_price, quantity_in_stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, merch.getMerchName());
            pstmt.setString(2, merch.getMerchType());
            pstmt.setDouble(3, merch.getMerchPrice());
            pstmt.setInt(4, merch.getQuantityInStock());

            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("Merchandise added: " + merch.getMerchName());
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error adding merchandise: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<GymMerch> getAllMerch() {
        List<GymMerch> merchList = new ArrayList<>();
        String sql = "SELECT * FROM gym_merch";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                merchList.add(new GymMerch(
                        rs.getInt("merch_id"),
                        rs.getString("merch_name"),
                        rs.getString("merch_type"),
                        rs.getDouble("merch_price"),
                        rs.getInt("quantity_in_stock")
                ));
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error retrieving merchandise: " + e.getMessage());
            e.printStackTrace();
        }
        return merchList;
    }

    public GymMerch getMerchById(int merchId) {
        String sql = "SELECT * FROM gym_merch WHERE merch_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, merchId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new GymMerch(
                        rs.getInt("merch_id"),
                        rs.getString("merch_name"),
                        rs.getString("merch_type"),
                        rs.getDouble("merch_price"),
                        rs.getInt("quantity_in_stock")
                );
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error retrieving merchandise: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateMerch(GymMerch merch) {
        String sql = "UPDATE gym_merch SET merch_name = ?, merch_type = ?, merch_price = ?, quantity_in_stock = ? WHERE merch_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, merch.getMerchName());
            pstmt.setString(2, merch.getMerchType());
            pstmt.setDouble(3, merch.getMerchPrice());
            pstmt.setInt(4, merch.getQuantityInStock());
            pstmt.setInt(5, merch.getMerchId());

            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("Merchandise updated: ID " + merch.getMerchId());
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error updating merchandise: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMerch(int merchId) {
        String sql = "DELETE FROM gym_merch WHERE merch_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, merchId);
            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("Merchandise deleted: ID " + merchId);
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error deleting merchandise: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public double getTotalStockValue() {
        String sql = "SELECT SUM(merch_price * quantity_in_stock) as total FROM gym_merch";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error calculating stock value: " + e.getMessage());
            e.printStackTrace();
        }
        return 0.0;
    }
}