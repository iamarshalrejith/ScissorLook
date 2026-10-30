package com.iamarshalrejith.service;

import com.iamarshalrejith.domain.PaymentMethod;
import com.iamarshalrejith.modal.PaymentOrder;
import com.iamarshalrejith.payload.dto.BookingDTO;
import com.iamarshalrejith.payload.dto.UserDTO;
import com.iamarshalrejith.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;

public interface PaymentService {
    PaymentLinkResponse createOrder(UserDTO user,BookingDTO booking, PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long id);
    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO user,Long amount,Long orderId);
    String createStripePaymentLink(UserDTO user,Long amount,Long orderId);
}
