package com.gym.service;

import com.gym.dao.GymMerchDAO;
import com.gym.model.GymMerch;

import java.util.List;

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
