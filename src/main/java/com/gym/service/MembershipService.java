package com.gym.service;

import java.util.List;

import com.gym.dao.MembershipDAO;
import com.gym.model.Membership;

public class MembershipService {
    private final MembershipDAO dao = new MembershipDAO();

    public boolean purchaseMembership(Membership membership) {
        return dao.addMembership(membership, membership.getUserId());
    }

    public List<Membership> getMembershipsByUserId(int userId) {
        return dao.getMembershipsByUserId(userId);
    }

    public double getTotalExpensesByUserId(int userId) {
        return getMembershipsByUserId(userId).stream()
                .mapToDouble(Membership::getMembershipCost)
                .sum();
    }
}
