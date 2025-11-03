package com.ecommerce.project.controller;
import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.ecommerce.project.config.AppConstants.SORT_CATEGORIES_BY;
import static com.ecommerce.project.config.AppConstants.SORT_DIR;

@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts (
            @RequestParam( name = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER, required = false ) Integer pageNumber,
            @RequestParam( name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false ) Integer pageSize,
            @RequestParam( name = "sortBy", defaultValue = SORT_CATEGORIES_BY, required = false ) String sortBy,
            @RequestParam( name = "sortOrder", defaultValue = SORT_DIR, required = false ) String sortOrder

    ){
        ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy , sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping ("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable  Long categoryId ,
             @RequestParam( name = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER, required = false ) Integer pageNumber,
             @RequestParam( name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false ) Integer pageSize,
             @RequestParam( name = "sortBy", defaultValue = SORT_CATEGORIES_BY, required = false ) String sortBy,
             @RequestParam( name = "sortOrder", defaultValue = SORT_DIR, required = false ) String sortOrder
    ) {
        ProductResponse productResponse = productService.searchByCategory(categoryId ,pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>( productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword (
            @PathVariable String keyword,
            @RequestParam( name = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER, required = false ) Integer pageNumber,
            @RequestParam( name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false ) Integer pageSize,
            @RequestParam( name = "sortBy", defaultValue = SORT_CATEGORIES_BY, required = false ) String sortBy,
            @RequestParam( name = "sortOrder", defaultValue = SORT_DIR, required = false ) String sortOrder
    ){
        ProductResponse productResponse = productService.searchProductByKeyword(keyword, pageNumber, pageSize,  sortBy,  sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct (@Valid @RequestBody ProductDTO productDTO,
                                                  @PathVariable Long categoryId){
        ProductDTO savedProductDTO =  productService.addProduct(categoryId, productDTO );
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }

    @PutMapping ("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct (@Valid @PathVariable Long productId, @RequestBody ProductDTO productDTO){
        ProductDTO updatedProductDTO = productService.updateProduct(  productId, productDTO );
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @DeleteMapping ("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct (@Valid @PathVariable Long productId){
       ProductDTO deletedProduct =  productService.deleteProduct( productId);
            return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }

    @PutMapping ("/admin/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage (@PathVariable Long productId,
                                                          @RequestParam("image") MultipartFile image) throws IOException {
        ProductDTO updatedProductDTO = productService.updateProductImage( productId, image);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

}

