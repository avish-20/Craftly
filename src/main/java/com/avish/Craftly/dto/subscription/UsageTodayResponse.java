package com.avish.Craftly.dto.subscription;

public record UsageTodayResponse(
        Integer tokensUsed,
        Integer tokenLimit,
        Integer previewRunning,
        Integer previewLimit
) {
}
