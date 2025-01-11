package com.simulator.credit.view;

import java.util.Scanner;

public class LoanView {

    public static String getInput(String prompt) {
        String value;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("%s: ", prompt);
            value = scanner.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return value;
    }

}
