package com.simulator.credit.controller;

import com.simulator.credit.model.LoanCalculator;
import com.simulator.credit.model.LoanModel;
import com.simulator.credit.model.NullEmptyChecker;
import com.simulator.credit.view.LoanView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoanController {

    private void doCalculateLoan(
            String vehicleType,
            String vehicleCondition,
            String vehicleYear,
            String loanAmount,
            String loanPeriod,
            String downPaymentAmount
    ) {
        LoanModel loanModel = new LoanModel(
                vehicleType,
                vehicleCondition,
                vehicleYear,
                loanAmount,
                loanPeriod,
                downPaymentAmount
        );
        LoanCalculator.creditCalculation(loanModel);
    }

    public void simulatorManual() {
        String vehicleType = LoanView.getInput("Input Jenis Kendaraan (Motor/Mobil)");
        String vehicleCondition = LoanView.getInput("Input Kendaraan (Bekas/Baru)");
        String vehicleYear = LoanView.getInput("Input Tahun Kendaraan (ex: 2024)");
        String loanAmount = LoanView.getInput("Input Jumlah Pinjaman Total (max: 1 miliar)");
        String loanPeriod = LoanView.getInput("Input Tenor Pinjaman (1-6 tahun)");
        String downPaymentAmount = LoanView.getInput("Input Jumlah DP");

        doCalculateLoan(
                vehicleType,
                vehicleCondition,
                vehicleYear,
                loanAmount,
                loanPeriod,
                downPaymentAmount
        );
    }

    public void simulatorTXTFile() {
        try {
            String filePath = LoanView.getInput("Input file path and filename (/path/your/file/file.txt)");
            File file = new File(filePath);

            if (NullEmptyChecker.isNullOrEmpty(file) || !file.exists()) {
                System.out.println("File not found");
                return;
            }

            if (!file.isFile()) {
                System.out.println("Directory is not a file");
                return;
            }

            if (!file.getName().endsWith(".txt")) {
                System.out.println("Extension file is not TXT");
                return;
            }

            System.out.println("TXT file found");

            readTXTFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void simulatorAPI() {
        try {
            String urlAPI = LoanView.getInput("Enter URL for API");
            System.out.println("Loading . . .");
            URL url = new URL(urlAPI);
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
                String vehicleYear = loan.getString("vehicleYear");
                String loanAmount = loan.getString("loanAmount");
                String loanPeriod = loan.getString("loanPeriod");
                String downPaymentAmount = loan.getString("downPaymentAmount");

                doCalculateLoan(
                        vehicleType,
                        vehicleCondition,
                        vehicleYear,
                        loanAmount,
                        loanPeriod,
                        downPaymentAmount
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readTXTFile(File file) {
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

                doCalculateLoan(
                        vehicleType,
                        vehicleCondition,
                        vehicleYear,
                        loanAmount,
                        loanPeriod,
                        downPaymentAmount
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
