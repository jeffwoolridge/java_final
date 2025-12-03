// File: src/main/java/com/gym/dao/WorkoutClassDAO.java
package com.app.dao;

import com.app.model.WorkoutClass;
import com.app.util.DatabaseConnection;
import com.app.util.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutClassDAO {
    private Connection connection;

    public WorkoutClassDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean createWorkoutClass(WorkoutClass workoutClass) {
        String sql = "INSERT INTO workout_classes (workout_class_type, workout_class_description, trainer_id, schedule_time, capacity) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, workoutClass.getWorkoutClassType());
            pstmt.setString(2, workoutClass.getWorkoutClassDescription());
            pstmt.setInt(3, workoutClass.getTrainerId());
            pstmt.setTimestamp(4, workoutClass.getScheduleTime());
            pstmt.setInt(5, workoutClass.getCapacity());

            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("Workout class created: " + workoutClass.getWorkoutClassType());
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error creating workout class: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<WorkoutClass> getAllWorkoutClasses() {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                classes.add(new WorkoutClass(
                        rs.getInt("workout_class_id"),
                        rs.getString("workout_class_type"),
                        rs.getString("workout_class_description"),
                        rs.getInt("trainer_id"),
                        rs.getTimestamp("schedule_time"),
                        rs.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error retrieving workout classes: " + e.getMessage());
            e.printStackTrace();
        }
        return classes;
    }

    public List<WorkoutClass> getWorkoutClassesByTrainer(int trainerId) {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, trainerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                classes.add(new WorkoutClass(
                        rs.getInt("workout_class_id"),
                        rs.getString("workout_class_type"),
                        rs.getString("workout_class_description"),
                        rs.getInt("trainer_id"),
                        rs.getTimestamp("schedule_time"),
                        rs.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error retrieving trainer classes: " + e.getMessage());
            e.printStackTrace();
        }
        return classes;
    }

    public boolean updateWorkoutClass(WorkoutClass workoutClass) {
        String sql = "UPDATE workout_classes SET workout_class_type = ?, workout_class_description = ?, schedule_time = ?, capacity = ? WHERE workout_class_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, workoutClass.getWorkoutClassType());
            pstmt.setString(2, workoutClass.getWorkoutClassDescription());
            pstmt.setTimestamp(3, workoutClass.getScheduleTime());
            pstmt.setInt(4, workoutClass.getCapacity());
            pstmt.setInt(5, workoutClass.getWorkoutClassId());

            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("Workout class updated: ID " + workoutClass.getWorkoutClassId());
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error updating workout class: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteWorkoutClass(int classId) {
        String sql = "DELETE FROM workout_classes WHERE workout_class_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, classId);
            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("Workout class deleted: ID " + classId);
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error deleting workout class: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}