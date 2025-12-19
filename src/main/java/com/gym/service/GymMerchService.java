package com.gym.service;

import java.util.List;

import com.gym.dao.GymMerchDAO;
import com.gym.model.GymMerch;

public class GymMerchService {

    private final GymMerchDAO dao = new GymMerchDAO();

    public void addMerch(GymMerch merch) {
        dao.addMerch(merch);
    }

    public List<GymMerch> getAllMerch() {
        return dao.getAllMerch();
    }

    public void deleteMerch(int id) {
        dao.deleteMerch(id);
    }

    public double getTotalStockValue() {
        return getAllMerch().stream().mapToDouble(m -> m.getMerchPrice() * m.getQuantityInStock()).sum();
    }
}
