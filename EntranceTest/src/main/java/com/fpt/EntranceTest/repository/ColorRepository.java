package com.fpt.EntranceTest.repository;

import com.fpt.EntranceTest.modal.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
}
