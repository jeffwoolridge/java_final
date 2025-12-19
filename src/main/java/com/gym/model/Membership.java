package com.gym.model;

import java.time.LocalDateTime;

public class Membership {
    private int membershipId;
    private String membershipType;
    private String membershipDescription;
    private double membershipCost;
    private int userId; // ✅ needed for DAO
    private LocalDateTime createdAt;

    public Membership() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public int getMembershipId() { return membershipId; }
    public void setMembershipId(int membershipId) { this.membershipId = membershipId; }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    public String getMembershipDescription() { return membershipDescription; }
    public void setMembershipDescription(String membershipDescription) { this.membershipDescription = membershipDescription; }

    public double getMembershipCost() { return membershipCost; }
    public void setMembershipCost(double membershipCost) { this.membershipCost = membershipCost; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
