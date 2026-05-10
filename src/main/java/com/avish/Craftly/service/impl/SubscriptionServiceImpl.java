package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.subscription.CheckoutRequest;
import com.avish.Craftly.dto.subscription.CheckoutResponse;
import com.avish.Craftly.dto.subscription.PortalResponse;
import com.avish.Craftly.dto.subscription.SubscriptionResponse;
import com.avish.Craftly.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubsciption(Long userId) {
        return null;
    }

}
