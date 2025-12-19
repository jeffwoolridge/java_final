package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Membership;
import com.gym.util.DBUtil;

public class MembershipDAO {

    // Add membership to DB
    public boolean addMembership(Membership m, int userId) {
        String sql = "INSERT INTO memberships (membership_type, membership_description, membership_cost, user_id, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getMembershipType());
            ps.setString(2, m.getMembershipDescription());
            ps.setDouble(3, m.getMembershipCost());
            ps.setInt(4, userId);
            ps.setTimestamp(5, Timestamp.valueOf(java.time.LocalDateTime.now()));

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all memberships for a user
    public List<Membership> getMembershipsByUserId(int userId) {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membership m = new Membership();
                m.setMembershipId(rs.getInt("membership_id"));
                m.setMembershipType(rs.getString("membership_type"));
                m.setMembershipDescription(rs.getString("membership_description"));
                m.setMembershipCost(rs.getDouble("membership_cost"));
                m.setUserId(rs.getInt("user_id"));
                m.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                memberships.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberships;
    }
}
