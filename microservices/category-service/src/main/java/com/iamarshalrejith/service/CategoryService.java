package com.iamarshalrejith.service;

import com.iamarshalrejith.modal.Category;
import com.iamarshalrejith.payload.dto.SalonDTO;

import java.util.Set;

public interface CategoryService {
    Category saveCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoriesBySalon(Long id);
    Category getCategoryById(Long id) throws Exception;
    void deleteCategoryById(Long id, Long salonId) throws Exception;
}
