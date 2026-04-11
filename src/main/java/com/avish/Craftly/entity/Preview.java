package com.avish.Craftly.entity;

import com.avish.Craftly.enums.PreviewStatus;

import java.time.Instant;

public class Preview {

    Long id;

    Project project;

    String namespace;//seperation of resources
    String podName;
    String previewUrl;

    PreviewStatus status;

    Instant startedAt;
    Instant terminatedAt;

    Instant createdAt;
}
