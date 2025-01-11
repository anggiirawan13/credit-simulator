package com.simulator.credit.model;

public class LoanCalculator {

    public void creditCalculation(LoanModel loanModel) {
        boolean isValid = LoanValidation.validation(loanModel);
        if (isValid) {
            double baseInterestRateMobil = 0.08; // 8%
            double baseInterestRateMotor = 0.09; // 9%
            double interestIncreaseOneYear = 0.001; // 0,1%;
            double interestIncreaseTwoYear = 0.005; // 0,5%;
            double loanAmount = Double.parseDouble(loanModel.getLoanAmount());
            double balanceLoanAmount = loanAmount - Double.parseDouble(loanModel.getDownPaymentAmount()) ;
            double installmentPrincipal = balanceLoanAmount / (12 * Integer.parseInt(loanModel.getLoanPeriod()));
            double baseInterestRate = loanModel.getVehicleType().equals("MOBIL") ? baseInterestRateMobil : baseInterestRateMotor;
            double installmentYearly = 0;

            for (int year = 1; year <= Integer.parseInt(loanModel.getLoanPeriod()); year++) {
                double baseIncreaseYear = year < 2 ? 0 : ((year - 1) % 2) == 0 ? interestIncreaseTwoYear : interestIncreaseOneYear;
                baseInterestRate += baseIncreaseYear;
                double tempBalance = balanceLoanAmount * (baseInterestRate) / 12;

                tempBalance += installmentPrincipal;
                installmentYearly += tempBalance * 12;

                System.out.printf("Tahun ke-%d: Rp. %.2f/bulan, Suku Bunga: %.2f\n", year, tempBalance, baseInterestRate * 100);
            }

            System.out.printf("Total Interest: Rp %.2f\n", installmentYearly);
            System.out.printf("Total Payment (exlude DP): Rp %.2f\n", balanceLoanAmount + installmentYearly);
            System.out.printf("Total Payment (include DP): Rp %.2f\n", loanAmount + installmentYearly);
        }
    }

}
