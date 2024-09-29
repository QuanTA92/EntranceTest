package com.fpt.EntranceTest.controller;

import com.fpt.EntranceTest.dto.request.ProductRequest;
import com.fpt.EntranceTest.dto.response.ProductResponse;
import com.fpt.EntranceTest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@ModelAttribute ProductRequest productRequest) {
        boolean isAdded = productService.addProduct(productRequest);

        if (isAdded) {
            return ResponseEntity.ok("Product added successfully!");
        } else {
            return ResponseEntity.status(500).body("Failed to add product.");
        }
    }

    @PutMapping("/update/{idProduct}")
    public ResponseEntity<?> updateProduct(@PathVariable int idProduct, @ModelAttribute ProductRequest productRequest) {
        boolean isUpdated = productService.updateProduct(idProduct, productRequest);

        if (isUpdated) {
            return ResponseEntity.ok("Product updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found or update failed.");
        }
    }

    @DeleteMapping("/delete/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable int idProduct) {
        boolean isDeleted = productService.deleteProduct(idProduct);

        if (isDeleted) {
            return ResponseEntity.ok("Product deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found or delete failed.");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        List<ProductResponse> productResponses = productService.getAllProducts();
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<?> getProductDetailsById(@PathVariable int idProduct) {
        List<ProductResponse> productResponses = productService.getProductDetailsById(idProduct);

        if (productResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        return ResponseEntity.ok(productResponses.get(0));
    }

    @GetMapping("/category/{idCategory}")
    public ResponseEntity<?> getProductDetailsByCategory(@PathVariable int idCategory) {
        List<ProductResponse> productResponses = productService.getAllProductsByCategory(idCategory);

        if (productResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found for this category.");
        }

        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/style/{idStyle}")
    public ResponseEntity<?> getProductDetailsByStyle(@PathVariable int idStyle) {
        List<ProductResponse> productResponses = productService.getAllProductsByStyle(idStyle);
        if (productResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found for this style.");
        }
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/color/{idColor}")
    public ResponseEntity<?> getProductDetailsByColor(@PathVariable int idColor) {
        List<ProductResponse> productResponses = productService.getAllProductsByColor(idColor);
        if (productResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found for this color.");
        }
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/size/{idSize}")
    public ResponseEntity<?> getProductDetailsBySize(@PathVariable int idSize) {
        List<ProductResponse> productResponses = productService.getAllProductsBySize(idSize);
        if (productResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found for this size.");
        }
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/price")
    public ResponseEntity<?> getProductsByPrice(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        try {
            List<ProductResponse> productResponses = productService.getAllProductsByPrice(minPrice, maxPrice);

            if (productResponses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no products in this price range.");
            }

            return new ResponseEntity<>(productResponses, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }



}
