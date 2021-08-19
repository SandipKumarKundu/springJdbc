package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Document(collection = "orders")
public class Orders {

    @Id
    String id;

    public Orders(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
    }

    List userDetails;
    Map<?,?> productDetails;
    String trackingDetail;
    @NotBlank(message = "Need User's details for creating order")
    String userId;
    @NotBlank(message = "Need Product name for creating order")
    String productId;
    Orders createOrder;

    public String getId() {
        return id;
    }

    public List getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List userDetails) {
        this.userDetails = userDetails;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Map<?, ?> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Map<?, ?> productDetails) {
        this.productDetails = productDetails;
    }

    public String getTrackingDetail() {
        return trackingDetail;
    }

    public void setTrackingDetail(String trackingDetail) {
        this.trackingDetail = trackingDetail;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    String paymentDetails;

}
