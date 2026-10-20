package com.iamarshalrejith.controller;


import com.iamarshalrejith.modal.ServiceOffering;
import com.iamarshalrejith.payload.dto.CategoryDTO;
import com.iamarshalrejith.payload.dto.SalonDTO;
import com.iamarshalrejith.payload.dto.ServiceDTO;
import com.iamarshalrejith.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {
    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            @RequestBody ServiceDTO serviceDTO
    ){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategoryId());

        ServiceOffering serviceOffering = serviceOfferingService.createService(salonDTO,serviceDTO,categoryDTO);
        return ResponseEntity.ok(serviceOffering);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(
            @PathVariable Long id,
            @RequestBody ServiceOffering serviceOffering
    ) throws Exception{
        ServiceOffering serviceOfferings = serviceOfferingService.updateService(id,serviceOffering);
        return ResponseEntity.ok(serviceOfferings);
    }
}
