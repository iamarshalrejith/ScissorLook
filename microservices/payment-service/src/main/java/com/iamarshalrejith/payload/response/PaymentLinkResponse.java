package com.iamarshalrejith.payload.response;

import lombok.Data;

@Data 
public class PaymentLinkResponse {
    private String payment_link_url;
    private String payment_link_id;
}
