package com.simulator.credit.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class LoanValidationTest {

    @Mock
    private LoanModel loanModel;

    @Test
    public void testValidLoan() {
        Mockito.when(loanModel.getVehicleType()).thenReturn("MOTOR");
        Mockito.when(loanModel.getVehicleCondition()).thenReturn("BARU");
        Mockito.when(loanModel.getVehicleYear()).thenReturn("2024");
        Mockito.when(loanModel.getLoanAmount()).thenReturn("100000000");
        Mockito.when(loanModel.getLoanPeriod()).thenReturn("3");
        Mockito.when(loanModel.getDownPaymentAmount()).thenReturn("25000000");

        boolean isValid = LoanValidation.validation(loanModel);
        assertTrue(isValid, "Loan validation should pass for valid input.");
    }

    @Test
    public void testInvalidVehicleType() {
        Mockito.when(loanModel.getVehicleType()).thenReturn("BIKE");

        boolean isValid = LoanValidation.validation(loanModel);
        assertFalse(isValid, "Loan validation should fail due to invalid vehicle type.");
    }

    @Test
    public void testInvalidVehicleCondition() {
        Mockito.when(loanModel.getVehicleType()).thenReturn("MOBIL");
        Mockito.when(loanModel.getVehicleCondition()).thenReturn("OLD");

        boolean isValid = LoanValidation.validation(loanModel);
        assertFalse(isValid, "Loan validation should fail due to invalid vehicle condition.");
    }

    @Test
    public void testInvalidVehicleYear() {
        Mockito.when(loanModel.getVehicleType()).thenReturn("MOBIL");
        Mockito.when(loanModel.getVehicleCondition()).thenReturn("BARU");
        Mockito.when(loanModel.getVehicleYear()).thenReturn("2020");

        boolean isValid = LoanValidation.validation(loanModel);
        assertFalse(isValid, "Loan validation should fail due to vehicle year out of range.");
    }

    @Test
    public void testInvalidLoanPeriod() {
        Mockito.when(loanModel.getVehicleType()).thenReturn("MOBIL");
        Mockito.when(loanModel.getVehicleCondition()).thenReturn("BARU");
        Mockito.when(loanModel.getVehicleYear()).thenReturn("2024");
        Mockito.when(loanModel.getLoanAmount()).thenReturn("500000000");
        Mockito.when(loanModel.getLoanPeriod()).thenReturn("7");

        boolean isValid = LoanValidation.validation(loanModel);
        assertFalse(isValid, "Loan validation should fail due to invalid loan period.");
    }

    @Test
    public void testInvalidDownPaymentAmount() {
        Mockito.when(loanModel.getVehicleType()).thenReturn("MOBIL");
        Mockito.when(loanModel.getVehicleCondition()).thenReturn("BARU");
        Mockito.when(loanModel.getVehicleYear()).thenReturn("2024");
        Mockito.when(loanModel.getLoanAmount()).thenReturn("500000000");
        Mockito.when(loanModel.getLoanPeriod()).thenReturn("3");
        Mockito.when(loanModel.getDownPaymentAmount()).thenReturn("100000000");

        boolean isValid = LoanValidation.validation(loanModel);
        assertFalse(isValid, "Loan validation should fail due to invalid down payment amount.");
    }

    @Test
    public void testEmptyLoanModel() {
        boolean isValid = LoanValidation.validation(loanModel);
        assertFalse(isValid, "Loan validation should fail due to empty loan model.");
    }
}
