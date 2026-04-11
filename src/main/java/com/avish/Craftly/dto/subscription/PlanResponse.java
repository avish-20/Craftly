package com.avish.Craftly.dto.subscription;

public record PlanResponse(
        Long id,

        String name,

        String stripePriceId,
        Integer maxProjects,
        Integer maxTokenPerDay,
        Integer maxPreviews,//maximum number of preview of code generated allowed
        Boolean unlimitedAi,//unlimitedAccesss to LLM ignore maxtoken per day

        Boolean active
) {
}
