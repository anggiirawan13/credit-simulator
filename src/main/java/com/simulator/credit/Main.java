package com.simulator.credit;

import com.simulator.credit.controller.LoanController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LoanController loanController = new LoanController();
        loanController.creditSimulator();
    }

}
