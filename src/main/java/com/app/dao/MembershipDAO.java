// File: src/main/java/com/gym/dao/MembershipDAO.java
package com.app.dao;

import com.app.model.Membership;
import com.app.util.DatabaseConnection;
import com.app.util.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {
    private Connection connection;

    public MembershipDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean createMembership(Membership membership) {
        String sql = "INSERT INTO memberships (membership_type, membership_description, membership_cost, member_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, membership.getMembershipType());
            pstmt.setString(2, membership.getMembershipDescription());
            pstmt.setDouble(3, membership.getMembershipCost());
            pstmt.setInt(4, membership.getMemberId());

            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("Membership created for user ID: " + membership.getMemberId());
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error creating membership: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                memberships.add(new Membership(
                        rs.getInt("membership_id"),
                        rs.getString("membership_type"),
                        rs.getString("membership_description"),
                        rs.getDouble("membership_cost"),
                        rs.getInt("member_id"),
                        rs.getTimestamp("purchase_date")
                ));
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error retrieving memberships: " + e.getMessage());
            e.printStackTrace();
        }
        return memberships;
    }

    public double getTotalRevenue() {
        String sql = "SELECT SUM(membership_cost) as total FROM memberships";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error calculating revenue: " + e.getMessage());
            e.printStackTrace();
        }
        return 0.0;
    }

    public List<Membership> getMembershipsByUserId(int userId) {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE member_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                memberships.add(new Membership(
                        rs.getInt("membership_id"),
                        rs.getString("membership_type"),
                        rs.getString("membership_description"),
                        rs.getDouble("membership_cost"),
                        rs.getInt("member_id"),
                        rs.getTimestamp("purchase_date")
                ));
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error retrieving user memberships: " + e.getMessage());
            e.printStackTrace();
        }
        return memberships;
    }

    public boolean deleteMembership(int membershipId) {
        String sql = "DELETE FROM memberships WHERE membership_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, membershipId);
            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("Membership deleted: ID " + membershipId);
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error deleting membership: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}