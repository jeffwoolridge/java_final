package com.gym.model;

public class WorkoutClass {
    private int classId;
    private String classType;
    private String classDescription;
    private int trainerId;
    private String scheduleTime;
    private int capacity;

    // Getters and setters
    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }

    public String getClassDescription() { return classDescription; }
    public void setClassDescription(String classDescription) { this.classDescription = classDescription; }

    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }

    public String getScheduleTime() { return scheduleTime; }
    public void setScheduleTime(String scheduleTime) { this.scheduleTime = scheduleTime; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
