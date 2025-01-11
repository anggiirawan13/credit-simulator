package com.simulator.credit.controller;

import com.simulator.credit.model.LoanCalculator;
import com.simulator.credit.model.LoanModel;
import com.simulator.credit.model.NullEmptyChecker;
import com.simulator.credit.view.LoanView;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;

public class LoanController {

    private LoanCalculator loanCalculator;

    public LoanController() {
        this.loanCalculator = new LoanCalculator();
    }

    public void simulatorManual() {
//        String vehicleType = LoanView.getInput("Input Jenis Kendaraan Motor | Mobil");
//        String vehicleCondition = LoanView.getInput("Input Kendaraan Bekas | Baru");
//        String vehicleYear = LoanView.getInput("Input Tahun Kendaraan");
//        String loanAmount = LoanView.getInput("Input Jumlah Pinjaman Total");
//        String loanPeriod = LoanView.getInput("Input Tenor Pinjaman");
//        String downPaymentAmount = LoanView.getInput("Input Jumlah DP");

        String vehicleType = "MOBIL";
        String vehicleCondition = "BARU";
        String vehicleYear = "2024";
        String loanAmount = "100000000";
        String loanPeriod = "3";
        String downPaymentAmount = "25000000";

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

    public void simulatorCSVFile() {
        try {
            File folder = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("file/csv")).toURI());
            File[] files = folder.listFiles();

            if (NullEmptyChecker.isNotNullOrEmpty(files)) {
                System.out.println("CSV file found");
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().endsWith(".csv")) {
                        System.out.println((i + 1) + ". " + files[i].getName());
                    }
                }

                String choice = LoanView.getInput("Enter choice for CSV file");
                File file = files[Integer.parseInt(choice) - 1];
                readCSVFile(file);
            }
        } catch (Exception e) {}
    }

    public void simulatorAPI() {
        try {
            URL url = new URL("https://run.mocky.io/v3/5f78065f-6632-4203-a73c-a9cce9d8e262");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            connection.disconnect();

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            System.out.println("Result: " + jsonArray);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject loan = jsonArray.getJSONObject(i);

                String vehicleType = loan.getString("vehicleType");
                String vehicleCondition = loan.getString("vehicleCondition");
                int vehicleYear = loan.getInt("vehicleYear");
                double loanAmount = loan.getDouble("loanAmount");
                int loanPeriod = loan.getInt("loanPeriod");
                double downPaymentAmount = loan.getDouble("downPaymentAmount");

                LoanModel loanModel = new LoanModel(
                        vehicleType,
                        vehicleCondition,
                        String.valueOf(vehicleYear),
                        String.valueOf(loanAmount),
                        String.valueOf(loanPeriod),
                        String.valueOf(downPaymentAmount)
                );
                loanCalculator.creditCalculation(loanModel);
            }
        } catch (Exception e) {}
    }

    private void readCSVFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                String line = br.readLine();
                if (NullEmptyChecker.isNullOrEmpty(line)) break;
                String[] split = line.split(",");
                if (split[0].equalsIgnoreCase("HEADER")) continue;

                String vehicleType = split[1];
                String vehicleCondition = split[2];
                String vehicleYear = split[3];
                String loanAmount = split[4];
                String loanPeriod = split[5];
                String downPaymentAmount = split[6];

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
