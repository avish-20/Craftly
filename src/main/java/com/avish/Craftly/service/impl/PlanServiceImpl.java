package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.subscription.PlanResponse;
import com.avish.Craftly.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
