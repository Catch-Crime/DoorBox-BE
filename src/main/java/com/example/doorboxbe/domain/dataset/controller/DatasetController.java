package com.example.doorboxbe.domain.dataset.controller;

import com.example.doorboxbe.domain.dataset.dto.response.DatasetResponseDTO;
import com.example.doorboxbe.domain.dataset.service.DatasetService;
import com.example.doorboxbe.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/datasets")
public class DatasetController {

    private final DatasetService datasetService;

    @GetMapping
    public ApiResponse<DatasetResponseDTO.DatasetResponse> datasetsByDate(@RequestParam LocalDate date) {
        DatasetResponseDTO.DatasetResponse response = datasetService.listForDate(date);

        return ApiResponse.onSuccess(response);
    }
}
