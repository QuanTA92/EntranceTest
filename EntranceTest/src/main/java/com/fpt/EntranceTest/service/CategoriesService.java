package com.fpt.EntranceTest.service;

import com.fpt.EntranceTest.dto.request.CategoriesRequest;
import com.fpt.EntranceTest.dto.response.CategoriesResponse;

import java.util.List;

public interface CategoriesService {

    boolean addCategory(CategoriesRequest categoriesRequest);

    boolean updateCategory(int idCategory, CategoriesRequest categoriesRequest);

    boolean deleteCategory(int idCategories);

    List<CategoriesResponse> getAllCategories();

    List<CategoriesResponse> getCategoriesById(int idCategory);
}
