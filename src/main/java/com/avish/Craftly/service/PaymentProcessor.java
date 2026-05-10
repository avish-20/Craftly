package com.avish.Craftly.service;

import com.avish.Craftly.dto.subscription.CheckoutRequest;
import com.avish.Craftly.dto.subscription.CheckoutResponse;
import com.avish.Craftly.dto.subscription.PortalResponse;
import com.stripe.model.StripeObject;

import java.util.Map;

public interface PaymentProcessor {
    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);

    PortalResponse openCustomerPortal(Long userId);

    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
