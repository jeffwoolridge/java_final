// File: src/main/java/com/gym/service/GymMerchService.java
package com.app.service;

import com.app.dao.GymMerchDAO;
import com.app.model.GymMerch;

import java.util.List;

public class GymMerchService {
    private GymMerchDAO gymMerchDAO;
    
    public GymMerchService() {
        this.gymMerchDAO = new GymMerchDAO();
    }
    
    public boolean addMerch(GymMerch merch) {
        // Validate merchandise data
        if (merch.getMerchPrice() < 0) {
            System.out.println("Invalid price: must be non-negative");
            return false;
        }
        
        if (merch.getQuantityInStock() < 0) {
            System.out.println("Invalid quantity: must be non-negative");
            return false;
        }
        
        return gymMerchDAO.createMerch(merch);
    }
    
    public List<GymMerch> getAllMerch() {
        return gymMerchDAO.getAllMerch();
    }
    
    public GymMerch getMerchById(int merchId) {
        return gymMerchDAO.getMerchById(merchId);
    }
    
    public boolean updateMerch(GymMerch merch) {
        // Validate merchandise data
        if (merch.getMerchPrice() < 0) {
            System.out.println("Invalid price: must be non-negative");
            return false;
        }
        
        if (merch.getQuantityInStock() < 0) {
            System.out.println("Invalid quantity: must be non-negative");
            return false;
        }
        
        return gymMerchDAO.updateMerch(merch);
    }
    
    public boolean deleteMerch(int merchId) {
        return gymMerchDAO.deleteMerch(merchId);
    }
    
    public double getTotalStockValue() {
        return gymMerchDAO.getTotalStockValue();
    }
    
    public void printMerchReport() {
        List<GymMerch> merchList = getAllMerch();
        System.out.println("\n=== Gym Merchandise Report ===");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", 
                         "ID", "Name", "Type", "Price", "Stock");
        System.out.println("-----------------------------------------------");
        
        for (GymMerch merch : merchList) {
            System.out.printf("%-5d %-20s %-15s $%-9.2f %-10d%n",
                merch.getMerchId(),
                merch.getMerchName(),
                merch.getMerchType(),
                merch.getMerchPrice(),
                merch.getQuantityInStock()
            );
        }
        
        System.out.println("-----------------------------------------------");
        System.out.printf("Total Stock Value: $%.2f%n", getTotalStockValue());
        System.out.println("===============================================\n");
    }
}