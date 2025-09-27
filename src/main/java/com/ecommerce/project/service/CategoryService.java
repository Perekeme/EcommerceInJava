package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import jakarta.validation.Valid;

public interface CategoryService {
 CategoryResponse getAllCategories( Integer pageNumber, Integer pageSize, String sortBy , String sortOrder);
 CategoryDTO createCategory( CategoryDTO categoryDTO);
 CategoryDTO deleteCategory(long categoryId);
 //Category updateCategory(long id, Category category);
 CategoryDTO updateCategory(CategoryDTO categoryDTO, long categoryId);

}


