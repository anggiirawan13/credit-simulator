package com.simulator.credit.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LoanCalculatorTest {

    @Mock
    private LoanModel loanModel;

    @Test
    public void testCreditCalculation() {
        MockitoAnnotations.openMocks(this);

        Mockito.when(loanModel.getVehicleType()).thenReturn("MOTOR");
        Mockito.when(loanModel.getVehicleCondition()).thenReturn("BARU");
        Mockito.when(loanModel.getVehicleYear()).thenReturn("2024");
        Mockito.when(loanModel.getLoanAmount()).thenReturn("100000000");
        Mockito.when(loanModel.getLoanPeriod()).thenReturn("3");
        Mockito.when(loanModel.getDownPaymentAmount()).thenReturn("25000000");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        LoanCalculator.creditCalculation(loanModel);

        String expectedOutput =
                "Tahun ke-1: Rp 2250000.00/bulan, Suku Bunga: 8.00, Total: Rp 27000000.00, Sisa Pinjaman: Rp 54000000.00\n" +
                        "Tahun ke-2: Rp 2432250.00/bulan, Suku Bunga: 8.10, Total: Rp 29187000.00, Sisa Pinjaman: Rp 29187000.00\n" +
                        "Tahun ke-3: Rp 2641423.50/bulan, Suku Bunga: 8.60, Total: Rp 31697082.00, Sisa Pinjaman: Rp 0.00\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
