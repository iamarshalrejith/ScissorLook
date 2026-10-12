package com.iamarshalrejith.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iamarshalrejith.modal.Salon;
import com.iamarshalrejith.payload.dto.SalonDTO;
import com.iamarshalrejith.payload.dto.UserDTO;
import com.iamarshalrejith.repository.SalonRepository;
import com.iamarshalrejith.service.SalonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {
        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setImages(req.getImages());
        salon.setOwnerId(user.getId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        salon.setPhoneNumber(req.getPhoneNumber());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId)
            throws Exception {

        Salon existingSalon = salonRepository.findById(salonId)
                .orElse(null);

        if (existingSalon == null) {
            throw new Exception("Salon does not exist");
        }

        if (!user.getId().equals(existingSalon.getOwnerId())) {
            throw new Exception("You are not authorized to update this salon");
        }

        existingSalon.setName(salon.getName());
        existingSalon.setAddress(salon.getAddress());
        existingSalon.setEmail(salon.getEmail());
        existingSalon.setCity(salon.getCity());
        existingSalon.setImages(salon.getImages());
        existingSalon.setOpenTime(salon.getOpenTime());
        existingSalon.setCloseTime(salon.getCloseTime());
        existingSalon.setPhoneNumber(salon.getPhoneNumber());

        return salonRepository.save(existingSalon);
    }


    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception {
        Salon salon = salonRepository.findById(salonId).orElse(null);
        if(salon == null){
            throw new Exception("Salon not exist");
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}