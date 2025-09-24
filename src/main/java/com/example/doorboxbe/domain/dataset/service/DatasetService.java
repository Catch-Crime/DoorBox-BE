package com.example.doorboxbe.domain.dataset.service;

import com.example.doorboxbe.domain.dataset.repository.DatasetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DatasetService implements DatasetServiceImpl{

    private final DatasetRepository datasetRepository;

}
