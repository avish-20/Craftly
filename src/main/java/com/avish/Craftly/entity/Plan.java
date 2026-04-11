package com.avish.Craftly.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {

    Long id;

    String name;

    String stripePriceId;
    Integer maxProjects;
    Integer maxTokenPerDay;
    Integer maxPreviews;//maximum number of preview of code generated allowed
    Boolean unlimitedAi;//unlimitedAccesss to LLM ignore maxtoken per day

    Boolean active;
}
