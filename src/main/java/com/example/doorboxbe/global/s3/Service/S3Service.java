package com.example.doorboxbe.global.s3.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.doorboxbe.global.config.S3Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class S3Service implements S3ServiceImpl {

    private final AmazonS3 s3;
    private final S3Config config;
    private final ObjectMapper om = new ObjectMapper();

    // 날짜 prefix 설정 : basePrefix/YYYY/MM/DD
    public String datePrefix(LocalDate date) {
        return String.format("%s/%04d/%02d/%02d/", config.getBasePrefix(), date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    // 날짜 폴더 내 모든 객체
    public List<S3ObjectSummary> listObjectsForDate(LocalDate date) {
        String datePrefix = datePrefix(date);
        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(config.getBucket())
                .withPrefix(datePrefix);
        ListObjectsV2Result result = s3.listObjectsV2(request);

        return new ArrayList<>(result.getObjectSummaries());
    }

    // 날짜 폴더 내 _result.json 수집
    public List<S3ObjectSummary> listResultJsonsForDate(LocalDate date) {
        return listObjectsForDate(date).stream()
                .filter(o -> o.getKey().endsWith("_result.json") || o.getKey().endsWith(".json"))
                .collect(Collectors.toList());
    }

    // json 파싱
    public <T> T readJson(String key, Class<T> type) {
        S3Object object = s3.getObject(new GetObjectRequest(config.getBucket(), key));
        try (InputStream in = object.getObjectContent()) {
            return om.readValue(in, type);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse json: s3://" + config.getBucket() + "/" + key, e);
        }
    }
}
