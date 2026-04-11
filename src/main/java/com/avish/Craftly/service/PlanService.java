package com.avish.Craftly.service;

import com.avish.Craftly.dto.subscription.PlanResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllActivePlans();
}
