package com.example.doorboxbe.domain.dataset.repository;

import com.example.doorboxbe.domain.dataset.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepository extends JpaRepository<Dataset, Long> {
}
