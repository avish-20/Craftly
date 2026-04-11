package com.avish.Craftly.service;

import com.avish.Craftly.dto.subscription.PlanLimitsResponse;
import com.avish.Craftly.dto.subscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {
    UsageTodayResponse getTodayUsage(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
