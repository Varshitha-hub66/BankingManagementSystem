package com.banking.model;

public class Customer {
    private int customerId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String passwordHash;

    public Customer() {}

    public Customer(String fullName, String email, String phone, String address, String passwordHash) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.passwordHash = passwordHash;
    }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
