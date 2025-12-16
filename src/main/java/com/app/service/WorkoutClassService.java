// File: src/main/java/com/gym/service/WorkoutClassService.java
package com.gym.service;

import com.gym.dao.WorkoutClassDAO;
import com.gym.model.WorkoutClass;

import java.util.List;

public class WorkoutClassService {
    private WorkoutClassDAO workoutClassDAO;
    
    public WorkoutClassService() {
        this.workoutClassDAO = new WorkoutClassDAO();
    }
    
    public boolean createWorkoutClass(WorkoutClass workoutClass) {
        // Validate workout class data
        if (workoutClass.getCapacity() < 1) {
            System.out.println("Invalid capacity: must be at least 1");
            return false;
        }
        
        if (workoutClass.getWorkoutClassType() == null || 
            workoutClass.getWorkoutClassType().trim().isEmpty()) {
            System.out.println("Invalid class type");
            return false;
        }
        
        return workoutClassDAO.createWorkoutClass(workoutClass);
    }
    
    public List<WorkoutClass> getAllWorkoutClasses() {
        return workoutClassDAO.getAllWorkoutClasses();
    }
    
    public List<WorkoutClass> getTrainerClasses(int trainerId) {
        return workoutClassDAO.getWorkoutClassesByTrainer(trainerId);
    }
    
    public boolean updateWorkoutClass(WorkoutClass workoutClass) {
        // Validate workout class data
        if (workoutClass.getCapacity() < 1) {
            System.out.println("Invalid capacity: must be at least 1");
            return false;
        }
        
        return workoutClassDAO.updateWorkoutClass(workoutClass);
    }
    
    public boolean deleteWorkoutClass(int classId) {
        return workoutClassDAO.deleteWorkoutClass(classId);
    }
}