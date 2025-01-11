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
                "Tahun ke-1: Rp 2270833.33/bulan, Suku Bunga: 9.00, Total: Rp 27250000.00, Sisa Pinjaman: Rp 54500000.00\n" +
                        "Tahun ke-2: Rp 2477479.17/bulan, Suku Bunga: 9.10, Total: Rp 29729750.00, Sisa Pinjaman: Rp 29729750.00\n" +
                        "Tahun ke-3: Rp 2715317.17/bulan, Suku Bunga: 9.60, Total: Rp 32583806.00, Sisa Pinjaman: Rp 0.00\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
