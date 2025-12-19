package com.gym.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.GymMerch;

public class GymMerchDAO {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/gymdb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";

    public void addMerch(GymMerch merch) {
        String sql = "INSERT INTO gymmerch (merchname, merctype, merchprice, quantityinstock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, merch.getMerchName());
            ps.setString(2, merch.getMerchType());
            ps.setDouble(3, merch.getMerchPrice());
            ps.setInt(4, merch.getQuantityInStock());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GymMerch> getAllMerch() {
        List<GymMerch> merchList = new ArrayList<>();
        String sql = "SELECT * FROM gymmerch";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                GymMerch merch = new GymMerch();
                merch.setMerchId(rs.getInt("merchid"));
                merch.setMerchName(rs.getString("merchname"));
                merch.setMerchType(rs.getString("merctype"));
                merch.setMerchPrice(rs.getDouble("merchprice"));
                merch.setQuantityInStock(rs.getInt("quantityinstock"));
                merchList.add(merch);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return merchList;
    }

    public void deleteMerch(int id) {
        String sql = "DELETE FROM gymmerch WHERE merchid = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
