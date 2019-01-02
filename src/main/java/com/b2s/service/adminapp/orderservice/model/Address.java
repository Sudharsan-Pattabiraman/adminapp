package com.b2s.service.adminapp.orderservice.model;

/**
 * Created by spattabiraman on 12/24/2018.
 */
public class Address {

    private static final long serialVersionUID = 1770143422670074062L;

    private Long platformOrderId;

    private String firstName;

    private String lastName;

    private String businessName;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String addressLine4;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String email;

    public Long getPlatformOrderId() {
        return platformOrderId;
    }

    public void setPlatformOrderId(Long platformOrderId) {
        this.platformOrderId = platformOrderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!getPlatformOrderId().equals(address.getPlatformOrderId())) return false;
        if (!getFirstName().equals(address.getFirstName())) return false;
        if (!getLastName().equals(address.getLastName())) return false;
        if (!getBusinessName().equals(address.getBusinessName())) return false;
        if (!getAddressLine1().equals(address.getAddressLine1())) return false;
        if (!getAddressLine2().equals(address.getAddressLine2())) return false;
        if (!getAddressLine3().equals(address.getAddressLine3())) return false;
        if (!getAddressLine4().equals(address.getAddressLine4())) return false;
        if (!getCity().equals(address.getCity())) return false;
        if (!getState().equals(address.getState())) return false;
        if (!getCountry().equals(address.getCountry())) return false;
        if (!getPostalCode().equals(address.getPostalCode())) return false;
        return getEmail().equals(address.getEmail());

    }

    @Override
    public int hashCode() {
        int result = getPlatformOrderId().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getBusinessName().hashCode();
        result = 31 * result + getAddressLine1().hashCode();
        result = 31 * result + getAddressLine2().hashCode();
        result = 31 * result + getAddressLine3().hashCode();
        result = 31 * result + getAddressLine4().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getState().hashCode();
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getPostalCode().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "platformOrderId=" + platformOrderId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", businessName='" + businessName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", addressLine3='" + addressLine3 + '\'' +
                ", addressLine4='" + addressLine4 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
