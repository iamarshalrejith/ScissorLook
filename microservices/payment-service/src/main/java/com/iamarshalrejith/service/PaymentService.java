package com.iamarshalrejith.service;

import com.iamarshalrejith.domain.PaymentMethod;
import com.iamarshalrejith.modal.PaymentOrder;
import com.iamarshalrejith.payload.dto.BookingDTO;
import com.iamarshalrejith.payload.dto.UserDTO;
import com.iamarshalrejith.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
    PaymentLinkResponse createOrder(UserDTO user,BookingDTO booking, PaymentMethod paymentMethod) throws RazorpayException, StripeException;
    PaymentOrder getPaymentOrderById(Long id) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO user,Long amount,Long orderId) throws RazorpayException;
    String createStripePaymentLink(UserDTO user,Long amount,Long orderId) throws StripeException;
}
