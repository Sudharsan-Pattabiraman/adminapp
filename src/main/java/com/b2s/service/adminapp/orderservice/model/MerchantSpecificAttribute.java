package com.b2s.service.adminapp.orderservice.model;

import java.util.Map;

/**
 * Created by spattabiraman on 12/27/2018.
 */
public class MerchantSpecificAttribute {

    private int merchantMessageId;

    private Map<String, String> attributes;

    private int orderLineId;

    public int getMerchantMessageId() {
        return merchantMessageId;
    }

    public void setMerchantMessageId(int merchantMessageId) {
        this.merchantMessageId = merchantMessageId;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public int getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MerchantSpecificAttribute)) return false;

        MerchantSpecificAttribute that = (MerchantSpecificAttribute) o;

        if (getMerchantMessageId() != that.getMerchantMessageId()) return false;
        if (getOrderLineId() != that.getOrderLineId()) return false;
        return getAttributes().equals(that.getAttributes());

    }

    @Override
    public int hashCode() {
        int result = getMerchantMessageId();
        result = 31 * result + getAttributes().hashCode();
        result = 31 * result + getOrderLineId();
        return result;
    }

    @Override
    public String toString() {
        return "MerchantSpecificAttribute{" +
                "merchantMessageId=" + merchantMessageId +
                ", attributes=" + attributes +
                ", orderLineId=" + orderLineId +
                '}';
    }
}
