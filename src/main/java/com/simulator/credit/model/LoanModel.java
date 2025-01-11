package com.simulator.credit.model;

public class LoanModel {

    private String vehicleType;
    private String vehicleCondition;
    private String vehicleYear;
    private String loanAmount;
    private String loanPeriod;
    private String downPaymentAmount;

    public LoanModel() {}

    public LoanModel(String vehicleType, String vehicleCondition, String vehicleYear, String loanAmount, String loanPeriod, String downPaymentAmount) {
        this.vehicleType = vehicleType;
        this.vehicleCondition = vehicleCondition;
        this.vehicleYear = vehicleYear;
        this.loanAmount = loanAmount;
        this.loanPeriod = loanPeriod;
        this.downPaymentAmount = downPaymentAmount;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(String loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public String getDownPaymentAmount() {
        return downPaymentAmount;
    }

    public void setDownPaymentAmount(String downPaymentAmount) {
        this.downPaymentAmount = downPaymentAmount;
    }
}
