package com.example.doorboxbe.domain.dataset.dto.response;

import com.example.doorboxbe.domain.dataset.entity.Dataset;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

public class DatasetResponseDTO {

    @Getter
    @Builder
    public static class DatasetResponse {
        private List<PhotoDetectionResponse> datasets;
        private Long cursor;
        private Boolean hasNext;

        public static DatasetResponse toListIdeaResponse(Slice<Dataset> datasetSlice) {
            List<Dataset> datasetList = datasetSlice.getContent();
            return DatasetResponse.builder()
                    .datasets(datasetList.stream().map(PhotoDetectionResponse::toPhotoDetectionResponse).toList())
                    .cursor(datasetSlice.hasNext() ? datasetList.get(datasetList.size() - 1).getId() : null)
                    .hasNext(datasetSlice.hasNext())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class PhotoDetectionResponse {
        private String day;
        private String time;
        private String emotion;
        private Boolean hasAccessory;
        private String gender;
        private String ageGroup;
        private String imageKey;

        public static PhotoDetectionResponse toPhotoDetectionResponse(Dataset dataset) {
            return PhotoDetectionResponse.builder()
                    .day(dataset.getDay())
                    .time(dataset.getTime())
                    .emotion(dataset.getEmotion())
                    .hasAccessory(dataset.getHasAccessory())
                    .gender(dataset.getGender())
                    .ageGroup(dataset.getAgeGroup())
                    .imageKey(dataset.getImageKey())
                    .build();
        }
    }
}
