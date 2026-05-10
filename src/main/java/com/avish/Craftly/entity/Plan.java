package com.avish.Craftly.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Plan {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(unique = true)
    String stripePriceId;


    Integer maxProjects;
    Integer maxTokenPerDay;
    Integer maxPreviews;//maximum number of preview of code generated allowed
    Boolean unlimitedAi;//unlimitedAccesss to LLM ignore maxtoken per day

    Boolean active;
}
