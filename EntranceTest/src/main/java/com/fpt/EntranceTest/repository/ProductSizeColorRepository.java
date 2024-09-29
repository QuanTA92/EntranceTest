package com.fpt.EntranceTest.repository;

import com.fpt.EntranceTest.modal.Product;
import com.fpt.EntranceTest.modal.ProductColorSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeColorRepository extends JpaRepository<ProductColorSize, Integer> {

    void deleteByProduct(Product existingProduct);

    List<ProductColorSize> findByColorId(int idColor);

    List<ProductColorSize> findBySizeId(int idSize);
}
