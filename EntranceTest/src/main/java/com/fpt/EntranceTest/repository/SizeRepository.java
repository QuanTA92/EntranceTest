package com.fpt.EntranceTest.repository;

import com.fpt.EntranceTest.modal.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
}
