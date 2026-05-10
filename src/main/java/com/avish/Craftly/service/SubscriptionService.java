package com.avish.Craftly.service;
import com.avish.Craftly.dto.subscription.CheckoutRequest;
import com.avish.Craftly.dto.subscription.CheckoutResponse;
import com.avish.Craftly.dto.subscription.PortalResponse;
import com.avish.Craftly.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubsciption(Long userId);


}
