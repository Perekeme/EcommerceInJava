package com.ecommerce.project.service;
import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategories( Integer pageNumber, Integer pageSize,  String sortBy , String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();
        if (categoryRepository.count() == 0 ) {
                throw new APIException("Category list is empty");
            }
        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOs);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }
//    @Override
//    public CategoryDTO createCategory(@Valid CategoryDTO categoryDTO) {
//        Category category = modelMapper.map(categoryDTO, Category.class);
//      Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
//       if (categoryFromDb != null)
//           throw new APIException("A category with  name "+ category.getCategoryName() +" already exists");
//        Category savedCategory = categoryRepository.save(category);
//        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
//        return savedCategoryDTO;
//    }
    @Override
    public CategoryDTO createCategory( CategoryDTO categoryDTO) {
        // Validate if the category name is already present
        if (categoryRepository.findByCategoryName(categoryDTO.getCategoryName()) != null) {
            throw new APIException(
                    "Category with the name '" + categoryDTO.getCategoryName() + "' already exists."
            );
        }
            // Map DTO -> Entity
            Category category = new Category();
            category.setCategoryName(categoryDTO.getCategoryName());

            //Save the new category and return as DTO
            Category savedCategory = categoryRepository.save(category);
            CategoryDTO savedCategoryDTO = new CategoryDTO(savedCategory.getCategoryId(), savedCategory.getCategoryName());
            return savedCategoryDTO;
    }

    @Override
    public CategoryDTO deleteCategory ( long categoryId ){
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        CategoryDTO categoryDTO = modelMapper.map(existingCategory, CategoryDTO.class);
     categoryRepository.delete(existingCategory);
     return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory( CategoryDTO categoryDTO, long categoryId ) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        category.setCategoryId(categoryId);
        Category savedCategory = categoryRepository.save(category);
      CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
        return savedCategoryDTO;
    }
}
