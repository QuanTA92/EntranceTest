package com.fpt.EntranceTest.controller;

import com.fpt.EntranceTest.dto.request.CategoriesRequest;
import com.fpt.EntranceTest.dto.response.CategoriesResponse;
import com.fpt.EntranceTest.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody CategoriesRequest categoriesRequest) {
        boolean isAdded = categoriesService.addCategory(categoriesRequest);
        if (isAdded) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add category.");
        }
    }

    @PutMapping("/update/{idCategory}")
    public ResponseEntity<String> updateCategory(@PathVariable int idCategory, @RequestBody CategoriesRequest categoriesRequest) {
        boolean isUpdated = categoriesService.updateCategory(idCategory, categoriesRequest);
        if (isUpdated) {
            return ResponseEntity.ok("Category updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found or update failed.");
        }
    }

    @DeleteMapping("/delete/{idCategories}")
    public ResponseEntity<String> deleteCategory(@PathVariable int idCategories) {
        boolean isDeleted = categoriesService.deleteCategory(idCategories);
        if (isDeleted) {
            return ResponseEntity.ok("Category deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found or delete failed.");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<CategoriesResponse>> getAllCategories() {
        List<CategoriesResponse> categoriesResponses = categoriesService.getAllCategories();
        return new ResponseEntity<>(categoriesResponses, HttpStatus.OK);
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoriesResponse> getCategoriesById(@PathVariable int idCategory) {
        List<CategoriesResponse> categoryResponseList = categoriesService.getCategoriesById(idCategory);

        if (categoryResponseList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(categoryResponseList.get(0));
    }

}
