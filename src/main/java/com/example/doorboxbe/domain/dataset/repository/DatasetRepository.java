package com.example.doorboxbe.domain.dataset.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;

public interface DatasetRepository extends JpaRepository<Data, Long> {
}
