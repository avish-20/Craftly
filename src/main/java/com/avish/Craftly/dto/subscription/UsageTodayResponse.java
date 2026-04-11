package com.avish.Craftly.dto.subscription;

public record UsageTodayResponse(
        int tokensUsed,
        int tokenLimit,
        int previewRunning,
        int previewLimit
) {
}
