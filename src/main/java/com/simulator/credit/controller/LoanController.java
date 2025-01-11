package com.simulator.credit.controller;

import com.simulator.credit.model.LoanCalculator;
import com.simulator.credit.model.LoanModel;
import com.simulator.credit.view.LoanView;

public class LoanController {

    private LoanCalculator loanCalculator;

    public LoanController() {
        this.loanCalculator = new LoanCalculator();
    }

    public void creditSimulator() {
//        String vehicleType = LoanView.getInput("Input Jenis Kendaraan Motor | Mobil");
//        String vehicleCondition = LoanView.getInput("Input Kendaraan Bekas | Baru");
//        String vehicleYear = LoanView.getInput("Input Tahun Kendaraan");
//        String loanAmount = LoanView.getInput("Input Jumlah Pinjaman Total");
//        String loanPeriod = LoanView.getInput("Input Tenor Pinjaman");
//        String downPaymentAmount = LoanView.getInput("Input Jumlah DP");

        String vehicleType = "MOBIL";
        String vehicleCondition = "BARU";
        String vehicleYear = "2024";
        String loanAmount = "1000000000";
        String loanPeriod = "4";
        String downPaymentAmount = "500000000";

        LoanModel loanModel = new LoanModel(
                vehicleType,
                vehicleCondition,
                vehicleYear,
                loanAmount,
                loanPeriod,
                downPaymentAmount
        );
        loanCalculator.creditCalculation(loanModel);
    }

}
