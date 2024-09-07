package com.project.EmployeeManagementSystem.exception;

public class ShiftDateInPastException extends Exception {
    public ShiftDateInPastException(String message) {
        super(message);
    }
}
