package com.gym.service;

import java.util.List;

import com.gym.dao.WorkoutClassDAO;
import com.gym.model.WorkoutClass;

public class WorkoutClassService {
    private final WorkoutClassDAO dao = new WorkoutClassDAO();

    // Add a new workout class
    public boolean addWorkoutClass(WorkoutClass wc) {
        return dao.addWorkoutClass(wc);
    }

    // Get all workout classes
    public List<WorkoutClass> getAllClasses() {
        return dao.getAllClasses();
    }

    // Delete a workout class by ID
    public boolean deleteWorkoutClass(int id) {
        return dao.deleteWorkoutClass(id);
    }
}
