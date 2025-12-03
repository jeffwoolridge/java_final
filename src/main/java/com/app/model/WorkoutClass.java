// File: src/main/java/com/gym/model/WorkoutClass.java
package com.app.model;

import java.sql.Timestamp;

public class WorkoutClass {
    private int workoutClassId;
    private String workoutClassType;
    private String workoutClassDescription;
    private int trainerId;
    private Timestamp scheduleTime;
    private int capacity;

    public WorkoutClass(int workoutClassId, String workoutClassType,
                        String workoutClassDescription, int trainerId,
                        Timestamp scheduleTime, int capacity) {
        this.workoutClassId = workoutClassId;
        this.workoutClassType = workoutClassType;
        this.workoutClassDescription = workoutClassDescription;
        this.trainerId = trainerId;
        this.scheduleTime = scheduleTime;
        this.capacity = capacity;
    }

    // Getters and Setters
    public int getWorkoutClassId() {
        return workoutClassId;
    }

    public void setWorkoutClassId(int id) {
        this.workoutClassId = id;
    }

    public String getWorkoutClassType() {
        return workoutClassType;
    }

    public void setWorkoutClassType(String type) {
        this.workoutClassType = type;
    }

    public String getWorkoutClassDescription() {
        return workoutClassDescription;
    }

    public void setWorkoutClassDescription(String desc) {
        this.workoutClassDescription = desc;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public Timestamp getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Timestamp time) {
        this.scheduleTime = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "WorkoutClass{" +
                "workoutClassId=" + workoutClassId +
                ", workoutClassType='" + workoutClassType + '\'' +
                ", trainerId=" + trainerId +
                ", scheduleTime=" + scheduleTime +
                ", capacity=" + capacity +
                '}';
    }
}