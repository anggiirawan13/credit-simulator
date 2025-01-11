package com.simulator.credit.model;

import java.time.Year;

public class LoanValidation {

    public static boolean validation(LoanModel loanModel) {
        if (NullEmptyChecker.isNullOrEmpty(loanModel)) System.out.println("Null or empty loan model");

        String vehicleType = loanModel.getVehicleType();
        if (NullEmptyChecker.isNullOrEmpty(vehicleType)) {
            System.out.println("Null or empty vehicle type");
            return false;
        } else if (!vehicleType.equalsIgnoreCase("MOTOR") && !vehicleType.equalsIgnoreCase("MOBIL")) {
            System.out.println("Vehicle type not supported");
            return false;
        }

        String vehicleCondition = loanModel.getVehicleCondition();
        if (NullEmptyChecker.isNullOrEmpty(vehicleCondition)) {
            System.out.println("Null or empty vehicle condition");
            return false;
        }  else if (!vehicleCondition.equalsIgnoreCase("BARU") && !vehicleCondition.equalsIgnoreCase("BEKAS")) {
            System.out.println("Vehicle condition not supported");
            return false;
        }

        String vehicleYear = loanModel.getVehicleYear();
        if (NullEmptyChecker.isNullOrEmpty(vehicleYear)) {
            System.out.println("Null or empty vehicle year");
            return false;
        } else if (vehicleYear.length() != 4) {
            System.out.println("Vehicle year not supported");
            return false;
        } else if (vehicleCondition.equalsIgnoreCase("BARU")) {
            int year = Year.now().getValue();
            if (Integer.parseInt(vehicleYear) < (year - 1)) {
                System.out.println("Vehicle year out of range");
                return false;
            }
        }

        if (NullEmptyChecker.isNullOrEmpty(loanModel.getLoanAmount())) {
            System.out.println("Null or empty loan amount");
            return false;
        }

        String loanPeriod = loanModel.getLoanPeriod();
        if (NullEmptyChecker.isNullOrEmpty(loanPeriod)) {
            System.out.println("Null or empty loan period");
            return false;
        } else {
            int loanPeriodInt = Integer.parseInt(loanPeriod);
            if (loanPeriodInt < 2 || loanPeriodInt > 6) {
                System.out.println("Invalid loan period");
                return false;
            }
        }

        if (NullEmptyChecker.isNullOrEmpty(loanModel.getDownPaymentAmount())) {
            System.out.println("Null or empty down payment amount");
            return false;
        } else {
            double downPaymentAmountInt = Integer.parseInt(loanModel.getDownPaymentAmount());
            if (vehicleType.equalsIgnoreCase("MOTOR")) {
                double loanAmountInt = Double.parseDouble(loanModel.getLoanAmount()) * 0.25;
                if (downPaymentAmountInt < loanAmountInt) {
                    System.out.println("Invalid down payment amount (25%)");
                    return false;
                }
            } else if (vehicleType.equalsIgnoreCase("MOBIL")) {
                double loanAmountInt = Double.parseDouble(loanModel.getLoanAmount()) * 0.35;
                if (downPaymentAmountInt < loanAmountInt) {
                    System.out.println("Invalid down payment amount (35%)");
                    return false;
                }
            }
        }

        return true;
    }

}
