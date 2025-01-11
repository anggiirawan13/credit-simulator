package com.simulator.credit.model;

public class LoanCalculator {

    public void creditCalculation(LoanModel loanModel) {
        boolean isValid = LoanValidation.validation(loanModel);
        if (isValid) {
            double bungaAwal = 0.08;
            double totalPinjaman = Double.parseDouble(loanModel.getLoanAmount()) - Double.parseDouble(loanModel.getDownPaymentAmount());
            for (int year = 1; year <= Integer.parseInt(loanModel.getLoanPeriod()); year++) {
                double tambahanBunga = year < 2 ? 0 : ((year - 1) % 2) == 0 ? 0.005 : 0.001;
                bungaAwal += tambahanBunga;

                totalPinjaman = totalPinjaman * (1 + bungaAwal);

                double cicilanPerBulan = totalPinjaman / ((12 * Integer.parseInt(loanModel.getLoanPeriod())) - (12 * (year - 1)));
                totalPinjaman -= cicilanPerBulan * 12;

                System.out.printf("Tahun ke-%d: Rp %.2f/bulan, Suku Bunga: %.2f, Total: Rp %.2f, Sisa Pinjaman: Rp %.2f\n",
                        year,
                        cicilanPerBulan,
                        bungaAwal * 100,
                        cicilanPerBulan * 12,
                        totalPinjaman < 1 ? 0 : totalPinjaman);
            }
        }
    }

}
