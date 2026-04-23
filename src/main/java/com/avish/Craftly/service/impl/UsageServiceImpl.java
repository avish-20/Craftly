package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.subscription.PlanLimitsResponse;
import com.avish.Craftly.dto.subscription.UsageTodayResponse;
import com.avish.Craftly.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsage(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
