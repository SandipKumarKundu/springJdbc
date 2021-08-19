package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "productInventory")
public class ProductsInventory {
    @Id
    String id;
    String name;
    String description;
    Float price;
    String sellerDetail;
    String sellerAddress;
    String quantity;
    List<String> recomentation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSellerDetail() {
        return sellerDetail;
    }

    public void setSellerDetail(String sellerDetail) {
        this.sellerDetail = sellerDetail;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<String> getRecomentation() {
        return recomentation;
    }

    public void setRecomentation(List<String> recomentation) {
        this.recomentation = recomentation;
    }

    public Map<?, ?> getProductPhysicalDetails() {
        return productPhysicalDetails;
    }

    public void setProductPhysicalDetails(Map<?, ?> productPhysicalDetails) {
        this.productPhysicalDetails = productPhysicalDetails;
    }

    Map<?,?> productPhysicalDetails;

}
