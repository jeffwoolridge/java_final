package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Membership;
import com.gym.util.DBUtil;

public class MembershipDAO {

    public boolean addMembership(Membership membership) {
        String sql = """
            INSERT INTO memberships (membership_type, membership_description, membership_cost, user_id)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, membership.getMembershipType());
            ps.setString(2, membership.getMembershipDescription());
            ps.setDouble(3, membership.getMembershipCost());
            ps.setInt(4, membership.getUserId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Membership> getMembershipsByUserId(int userId) {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE user_id = ? ORDER BY membership_id";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                memberships.add(extractMembershipFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberships;
    }

    public double getTotalExpensesByUserId(int userId) {
        String sql = "SELECT SUM(membership_cost) AS total FROM memberships WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private Membership extractMembershipFromResultSet(ResultSet rs) throws SQLException {
        Membership membership = new Membership();
        membership.setMembershipId(rs.getInt("membership_id"));
        membership.setMembershipType(rs.getString("membership_type"));
        membership.setMembershipDescription(rs.getString("membership_description"));
        membership.setMembershipCost(rs.getDouble("membership_cost"));
        membership.setUserId(rs.getInt("user_id"));
        membership.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return membership;
    }
}
