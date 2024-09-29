package com.fpt.EntranceTest.repository;

import com.fpt.EntranceTest.modal.Image;
import com.fpt.EntranceTest.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    void deleteByProduct(Product existingProduct);

}
