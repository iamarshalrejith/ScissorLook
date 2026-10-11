package com.iamarshalrejith.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iamarshalrejith.modal.Salon;
import com.iamarshalrejith.payload.dto.SalonDTO;
import com.iamarshalrejith.payload.dto.UserDTO;
import com.iamarshalrejith.service.SalonService;

@Service
public class SalonServiceImpl implements SalonService {

    @Override
    public Salon createSalon(SalonDTO salon, UserDTO user) {
        // TODO: Implement logic
        return null;
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) {
        // TODO: Implement logic
        return null;
    }

    @Override
    public List<Salon> getAllSalons() {
        // TODO: Implement logic
        return null;
    }

    @Override
    public Salon getSalonById(Long salonId) {
        // TODO: Implement logic
        return null;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        // TODO: Implement logic
        return null;
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        // TODO: Implement logic
        return null;
    }
}