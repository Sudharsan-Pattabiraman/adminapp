package com.b2s.service.adminapp.model;

/**
 * Created by spattabiraman on 10/25/2018.
 */
public class ProductInfo {

    private String productName;

    private int productPoints;

    private String productId;

    public ProductInfo(String productName, int productPoints, String productId) {
        this.productName = productName;
        this.productPoints = productPoints;
        this.productId = productId;
    }

    public ProductInfo() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPoints() {
        return productPoints;
    }

    public void setProductPoints(int productPoints) {
        this.productPoints = productPoints;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductInfo that = (ProductInfo) o;

        if (productPoints != that.productPoints) return false;
        if (!productName.equals(that.productName)) return false;
        return productId.equals(that.productId);

    }

    @Override
    public int hashCode() {
        int result = productName.hashCode();
        result = 31 * result + productPoints;
        result = 31 * result + productId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productName='" + productName + '\'' +
                ", productPoints=" + productPoints +
                ", productId='" + productId + '\'' +
                '}';
    }
}
