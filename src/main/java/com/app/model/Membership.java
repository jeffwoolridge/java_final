// File: src/main/java/com/gym/model/Membership.java
package com.app.model;

import java.sql.Timestamp;

public class Membership {
    private int membershipId;
    private String membershipType;
    private String membershipDescription;
    private double membershipCost;
    private int memberId;
    private Timestamp purchaseDate;

    public Membership(int membershipId, String membershipType, String membershipDescription,
                      double membershipCost, int memberId, Timestamp purchaseDate) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberId = memberId;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters
    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipDescription() {
        return membershipDescription;
    }

    public void setMembershipDescription(String desc) {
        this.membershipDescription = desc;
    }

    public double getMembershipCost() {
        return membershipCost;
    }

    public void setMembershipCost(double cost) {
        this.membershipCost = cost;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp date) {
        this.purchaseDate = date;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "membershipId=" + membershipId +
                ", membershipType='" + membershipType + '\'' +
                ", membershipCost=" + membershipCost +
                ", memberId=" + memberId +
                '}';
    }
}