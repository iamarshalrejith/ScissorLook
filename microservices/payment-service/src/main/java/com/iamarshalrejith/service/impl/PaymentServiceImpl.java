package com.iamarshalrejith.service.impl;

import com.iamarshalrejith.domain.PaymentMethod;
import com.iamarshalrejith.modal.PaymentOrder;
import com.iamarshalrejith.payload.dto.BookingDTO;
import com.iamarshalrejith.payload.dto.UserDTO;
import com.iamarshalrejith.payload.response.PaymentLinkResponse;
import com.iamarshalrejith.repository.PaymentOrderRepository;
import com.iamarshalrejith.service.PaymentService;
import com.razorpay.PaymentLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentOrderRepository paymentOrderRepository;

    @Override
    public PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return null;
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO user, Long amount, Long orderId) {
        return null;
    }

    @Override
    public String createStripePaymentLink(UserDTO user, Long amount, Long orderId) {
        return "";
    }
}
