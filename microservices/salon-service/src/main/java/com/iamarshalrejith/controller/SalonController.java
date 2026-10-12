package com.iamarshalrejith.controller;

import com.iamarshalrejith.modal.Salon;
import com.iamarshalrejith.payload.dto.SalonDTO;
import com.iamarshalrejith.payload.dto.UserDTO;
import com.iamarshalrejith.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController{
    private final SalonService salonService;

    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.createSalon(salonDTO,userDTO);
        return null;
    }
}