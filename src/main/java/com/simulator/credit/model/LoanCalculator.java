package com.simulator.credit.model;

public class LoanCalculator {

    public static final double BASE_INTEREST_RATE_MOBIL = 0.08;
    public static final double BASE_INTEREST_RATE_MOTOR = 0.09;
    public static final double INCREASE_INTEREST_RATE_ODD = 0.001;
    public static final double INCREASE_INTEREST_RATE_EVEN = 0.005;

    public static void creditCalculation(LoanModel loanModel) {
        boolean isValid = LoanValidation.validation(loanModel);
        if (isValid) {
            double baseInterestRate = loanModel.getVehicleType().equalsIgnoreCase("MOBIL") ? BASE_INTEREST_RATE_MOBIL : BASE_INTEREST_RATE_MOTOR;
            double loanAmount = Double.parseDouble(loanModel.getLoanAmount()) - Double.parseDouble(loanModel.getDownPaymentAmount());
            for (int year = 1; year <= Integer.parseInt(loanModel.getLoanPeriod()); year++) {
                double increaseInterestRate = year < 2 ? 0 : ((year - 1) % 2) == 0 ? INCREASE_INTEREST_RATE_EVEN : INCREASE_INTEREST_RATE_ODD;
                baseInterestRate += increaseInterestRate;

                loanAmount = loanAmount * (1 + baseInterestRate);

                double installmentMonthly = loanAmount / ((12 * Integer.parseInt(loanModel.getLoanPeriod())) - (12 * (year - 1)));
                loanAmount -= installmentMonthly * 12;

                System.out.printf("Tahun ke-%d: Rp %.2f/bulan, Suku Bunga: %.2f, Total: Rp %.2f, Sisa Pinjaman: Rp %.2f\n",
                        year,
                        installmentMonthly,
                        baseInterestRate * 100,
                        installmentMonthly * 12,
                        loanAmount < 1 ? 0 : loanAmount);
            }
        }
    }

}
