package com.simulator.credit;

import com.simulator.credit.controller.LoanController;
import com.simulator.credit.view.LoanView;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            String filename = args[0];
            System.out.println("Loading file: " + filename);
            new LoanController().readTXTFile(new File(filename));
            return;
        }

        creditSimulator();
    }

    public static void creditSimulator() {
        LoanController loanController = new LoanController();

        System.out.println("Selamat Datang  di Aplikasi Credit Simulator!");
        System.out.println("============= DAFTAR MENU =============");
        System.out.println("1. Console");
        System.out.println("2. TXT File");
        System.out.println("3. API");
        System.out.println("4. Keluar");

        while (true) {
            String choice = LoanView.getInput("Pilih Menu (1/2/3/4)");
            switch (choice) {
                case "1":
                    loanController.simulatorManual();
                    break;
                case "2":
                    loanController.simulatorTXTFile();
                    break;
                case "3":
                    loanController.simulatorAPI();
                    break;
                case "4":
                    System.out.println("Terima Kasih!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Menu tidak ditemukan");
                    break;
            }

            String option = LoanView.getInput("Apakah anda ingin mengkalkulasi lagi? (Y/n)");
            if (option.equalsIgnoreCase("n")) {
                System.out.println("Terima Kasih!");
                System.exit(0);
                return;
            }
        }
    }

}
