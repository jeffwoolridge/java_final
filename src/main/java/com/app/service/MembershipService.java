// File: src/main/java/com/gym/service/MembershipService.java
package com.gym.service;

import com.gym.dao.MembershipDAO;
import com.gym.model.Membership;

import java.util.List;

public class MembershipService {
    private MembershipDAO membershipDAO;
    
    public MembershipService() {
        this.membershipDAO = new MembershipDAO();
    }
    
    public boolean purchaseMembership(Membership membership) {
        // Validate membership data
        if (membership.getMembershipCost() < 0) {
            System.out.println("Invalid membership cost");
            return false;
        }
        
        if (membership.getMemberId() <= 0) {
            System.out.println("Invalid member ID");
            return false;
        }
        
        return membershipDAO.createMembership(membership);
    }
    
    public List<Membership> getAllMemberships() {
        return membershipDAO.getAllMemberships();
    }
    
    public double getTotalRevenue() {
        return membershipDAO.getTotalRevenue();
    }
    
    public double getUserTotalExpenses(int userId) {
        List<Membership> userMemberships = membershipDAO.getMembershipsByUserId(userId);
        return userMemberships.stream()
                .mapToDouble(Membership::getMembershipCost)
                .sum();
    }
    
    public List<Membership> getUserMemberships(int userId) {
        return membershipDAO.getMembershipsByUserId(userId);
    }
    
    public boolean deleteMembership(int membershipId) {
        return membershipDAO.deleteMembership(membershipId);
    }
}