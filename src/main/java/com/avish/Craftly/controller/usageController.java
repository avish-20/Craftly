package com.avish.Craftly.controller;

import com.avish.Craftly.dto.subscription.PlanLimitsResponse;
import com.avish.Craftly.dto.subscription.UsageTodayResponse;
import com.avish.Craftly.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usage")
@RequiredArgsConstructor
public class usageController {
    private final UsageService usageService;

    @GetMapping("/today")
    public ResponseEntity<UsageTodayResponse> getUsageToday(){
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getTodayUsage(userId));
    }

    @GetMapping("/limits")
    public ResponseEntity<PlanLimitsResponse> getPlanLimit(){
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getCurrentSubscriptionLimitsOfUser(userId));
    }

}
