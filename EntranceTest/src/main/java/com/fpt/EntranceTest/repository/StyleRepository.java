package com.fpt.EntranceTest.repository;

import com.fpt.EntranceTest.modal.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer> {
}
