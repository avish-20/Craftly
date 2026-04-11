package com.avish.Craftly.entity;

import java.time.Instant;

public class UsageLog {
    Long id;
    User user;
    Project project;

    String action;

    Integer tokenUsed;
    Integer durationMs;

    String metaData;//JSON of{Model_used, promt_used}

    Instant createdAt;


}
