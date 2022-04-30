package com.idigital.epam.energy.service.DTO;

public class RequestPayment {
    private String title = "Payment for energy";
    private String description = "Please pay in  5days";
    private String serviceName = "ENERGY";
    private Integer amount;
    private String citizenCardNumber;

    public RequestPayment(String title, String description, String serviceName, Integer amount, String citizenCardNumber) {
        this.title = title;
        this.description = description;
        this.serviceName = serviceName;
        this.amount = amount;
        this.citizenCardNumber = citizenCardNumber;
    }

    public RequestPayment(Integer amount, String citizenCardNumber) {
        this.amount = amount;
        this.citizenCardNumber = citizenCardNumber;
    }

    public RequestPayment() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCitizenCardNumber() {
        return citizenCardNumber;
    }

    public void setCitizenCardNumber(String citizenCardNumber) {
        this.citizenCardNumber = citizenCardNumber;
    }
}
