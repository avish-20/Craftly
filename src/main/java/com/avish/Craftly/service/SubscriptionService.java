package com.avish.Craftly.service;
import com.avish.Craftly.dto.subscription.CheckoutRequest;
import com.avish.Craftly.dto.subscription.CheckoutResponse;
import com.avish.Craftly.dto.subscription.PortalResponse;
import com.avish.Craftly.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubsciption(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
