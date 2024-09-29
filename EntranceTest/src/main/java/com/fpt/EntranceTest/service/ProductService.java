package com.fpt.EntranceTest.service;

import com.fpt.EntranceTest.dto.request.ProductRequest;
import com.fpt.EntranceTest.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    boolean addProduct(ProductRequest productRequest);

    boolean updateProduct(int idProduct, ProductRequest productRequest);

    boolean deleteProduct(int idProduct);

    List<ProductResponse> getAllProducts();

    List<ProductResponse> getProductDetailsById(int idProduct);

    List<ProductResponse> getAllProductsByCategory(int idCategory);

    List<ProductResponse> getAllProductsByStyle(int idStyle);

    List<ProductResponse> getAllProductsByColor(int idColor);

    List<ProductResponse> getAllProductsBySize(int idSize);

    List<ProductResponse> getAllProductsByPrice(double minPrice, double maxPrice);
}
