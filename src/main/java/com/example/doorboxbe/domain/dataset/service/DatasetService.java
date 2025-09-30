package com.example.doorboxbe.domain.dataset.service;

import com.example.doorboxbe.domain.dataset.dto.response.DatasetResponseDTO;
import com.example.doorboxbe.domain.dataset.entity.Dataset;
import com.example.doorboxbe.domain.dataset.repository.DatasetRepository;
import com.example.doorboxbe.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class DatasetService implements DatasetServiceImpl{

    private final S3Service s3Service;
    private final DatasetRepository datasetRepository;

    public DatasetResponseDTO.DatasetResponse listForDate(LocalDate date, Long cursor, Integer size) {

    }

    private Dataset update(Dataset dataset) {

    }
}
