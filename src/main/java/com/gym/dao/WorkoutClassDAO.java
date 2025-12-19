package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.WorkoutClass;
import com.gym.util.DBUtil;

public class WorkoutClassDAO {

    // Add a new workout class
    public boolean addWorkoutClass(WorkoutClass wc) {
        String sql = "INSERT INTO workout_classes (class_type, class_description, trainer_id, schedule_time, capacity) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, wc.getClassType());
            ps.setString(2, wc.getClassDescription());
            ps.setInt(3, wc.getTrainerId());
            ps.setString(4, wc.getScheduleTime()); // Assuming scheduleTime is a String. Use Timestamp if it's a DateTime
            ps.setInt(5, wc.getCapacity());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all workout classes
    public List<WorkoutClass> getAllClasses() {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes ORDER BY class_id";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                WorkoutClass wc = new WorkoutClass();
                wc.setClassId(rs.getInt("class_id"));
                wc.setClassType(rs.getString("class_type"));
                wc.setClassDescription(rs.getString("class_description"));
                wc.setTrainerId(rs.getInt("trainer_id"));
                wc.setScheduleTime(rs.getString("schedule_time")); // Match your model type
                wc.setCapacity(rs.getInt("capacity"));
                classes.add(wc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classes;
    }

    // Delete a workout class by ID
    public boolean deleteWorkoutClass(int id) {
        String sql = "DELETE FROM workout_classes WHERE class_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
