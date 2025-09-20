package com.example.doorboxbe.domain.dataset.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "dataset")
public class Dataset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dataset_id")
    private Long id;

    private String day;
    private String time;
    private String emotion;

    @Column(name = "has_mask")
    private Boolean hasMask;
    private String gender;

    @Column(name = "age_group")
    private String ageGroup;

    @Column(name = "image_key")
    private String imageKey;
}
