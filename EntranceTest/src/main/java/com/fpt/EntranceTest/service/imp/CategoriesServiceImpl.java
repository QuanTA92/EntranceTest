package com.fpt.EntranceTest.service.imp;

import com.fpt.EntranceTest.dto.request.CategoriesRequest;
import com.fpt.EntranceTest.dto.response.CategoriesResponse;
import com.fpt.EntranceTest.modal.Categories;
import com.fpt.EntranceTest.repository.CategoriesRepository;
import com.fpt.EntranceTest.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public boolean addCategory(CategoriesRequest categoriesRequest) {
        try {
            Categories category = new Categories();
            category.setName(categoriesRequest.getName());
            category.setCreateDate(new Date());

            categoriesRepository.save(category);

            return true;
        } catch (Exception e) {
            System.err.println("Error adding category: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateCategory(int idCategory, CategoriesRequest categoriesRequest) {
        Optional<Categories> categoryOptional = categoriesRepository.findById(idCategory);
        if (categoryOptional.isPresent()) {
            Categories category = categoryOptional.get();
            category.setName(categoriesRequest.getName());
            categoriesRepository.save(category);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(int idCategories) {
        if (categoriesRepository.existsById(idCategories)) {
            categoriesRepository.deleteById(idCategories);
            return true;
        }
        return false;
    }

    @Override
    public List<CategoriesResponse> getAllCategories() {
        List<Categories> categoriesList = categoriesRepository.findAll();
        List<CategoriesResponse> responseList = new ArrayList<>();

        for (Categories category : categoriesList) {
            CategoriesResponse response = new CategoriesResponse();
            response.setName(category.getName());
            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public List<CategoriesResponse> getCategoriesById(int idCategory) {
        List<CategoriesResponse> categoriesResponses = new ArrayList<>();

        try {
            Categories category = categoriesRepository.findById(idCategory).orElse(null);

            if (category != null) {
                CategoriesResponse response = new CategoriesResponse();
                response.setName(category.getName());
                categoriesResponses.add(response);
            } else {
                System.out.println("Category not found with ID: " + idCategory);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving category: " + e.getMessage());
        }

        return categoriesResponses;
    }


}
