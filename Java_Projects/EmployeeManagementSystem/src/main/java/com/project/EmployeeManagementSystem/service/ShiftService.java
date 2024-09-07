package com.project.EmployeeManagementSystem.service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.project.EmployeeManagementSystem.exception.*;
import com.project.EmployeeManagementSystem.model.Employee;
import com.project.EmployeeManagementSystem.model.Shift;
import com.project.EmployeeManagementSystem.repository.EmployeeRepository;
import com.project.EmployeeManagementSystem.repository.ShiftRepository;

public class ShiftService {
	static Scanner sc = new Scanner(System.in);

    private static void validateShiftId(int shiftId) throws InvalidShiftIdException {
        if (shiftId <= 0) {
            throw new InvalidShiftIdException("Shift ID must be a positive integer");
        }
    }

    private static void validateShiftDate(Date shiftDate) throws ShiftDateInPastException {
        if (shiftDate!=null && shiftDate.before(new java.util.Date())) {
            throw new ShiftDateInPastException("Shift date cannot be in the past");
        }
    }
    private static Employee checkEmployeeExists(int employeeId) throws EmployeeNotFoundException {
        Employee emp = EmployeeRepository.employeeExists(employeeId);
    	if (emp==null) {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
        }else return emp;
    }
    private static Shift checkShift(int id) throws ShiftNotFoundException {
        Shift s = ShiftRepository.checkShift(id);
    	if (s==null) {
            throw new ShiftNotFoundException("Shift with ID " + id + " not found");
        }else return s;
    }


    public static void addShift() {
        int employee_id;
        String shift_date, start_time, end_time;
        System.out.print("Enter employee_id: ");
        employee_id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter shift_date in format (dd-MM-yyyy): ");
        shift_date = sc.nextLine();
        System.out.print("Enter start_time in 24 hrs format (HH:mm): ");
        start_time = sc.nextLine();
        System.out.print("Enter end_time in 24 hrs format (HH:mm): ");
        end_time = sc.nextLine();

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Time startTime = Time.valueOf(LocalTime.parse(start_time, formatter1));
            Time endTime = Time.valueOf(LocalTime.parse(end_time, formatter1));
            Date shiftDate = !shift_date.isEmpty() ? new Date(formatter2.parse(shift_date).getTime()) : null;

            Employee emp = checkEmployeeExists(employee_id);
            validateShiftDate(shiftDate);
            

            if (startTime.compareTo(endTime) >= 0) {
                throw new IllegalArgumentException("Start Time should be before end time");
            }

            ShiftRepository.addShift(new Shift(emp,shiftDate, startTime, endTime));

        } catch (IllegalArgumentException | ShiftDateInPastException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect Time Format");
        } catch (ParseException e) {
            System.out.println("Incorrect Date Format");
        }
    }

    public static void getShiftDetails() {
    	List<Shift> shift=ShiftRepository.getShiftDetails();
    	if(shift!=null) {
	        System.out.printf("%-15s %-20s %-25s %-15s %-20s%n",
	                "Shift Id", "Employee Id", "Start Time", "End Time", "Shift Date");
	        System.out.println("----------------------------------------------------------------------------");
	        for(Shift s:shift) {
	        	System.out.printf("%-15s %-20s %-25s %-15s %-20s%n",
	                    s.getId(), s.getEmployee().getId(), s.getStart_time(), s.getEnd_time(),s.getShift_date());
	        }
	        System.out.println("----------------------------------------------------------------------------");
	    }
    }

    public static void updateShift() {
        System.out.print("Enter Shift Id for update: ");
        int id = sc.nextInt();
        int employee_id;
        String shift_date, start_time, end_time;
        System.out.print("Enter employee_id: ");
        employee_id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter shift_date in format (dd-MM-yyyy): ");
        shift_date = sc.nextLine();
        System.out.print("Enter start_time in 24 hrs format (HH:mm): ");
        start_time = sc.nextLine();
        System.out.print("Enter end_time in 24 hrs format (HH:mm): ");
        end_time = sc.nextLine();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            validateShiftId(id);
            Time startTime = Time.valueOf(LocalTime.parse(start_time, formatter1));
            Time endTime = Time.valueOf(LocalTime.parse(end_time, formatter1));
            Date shiftDate = !shift_date.isEmpty() ? new Date(formatter2.parse(shift_date).getTime()) : null;

            Employee emp =checkEmployeeExists(employee_id);
            validateShiftDate(shiftDate);


            if (startTime.compareTo(endTime) >= 0) {
                throw new IllegalArgumentException("Start Time should be before end time");
            }

            ShiftRepository.updateShift(
            		new Shift(emp,shiftDate, startTime, endTime), id);

        } catch (DateTimeParseException e) {
            System.out.println("Incorrect Time Format");
        } catch (ParseException e) {
            System.out.println("Incorrect Date Format");
        } catch (InvalidShiftIdException | ShiftDateInPastException | IllegalArgumentException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteShift() {
        System.out.print("Enter Shift Id to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        try {
            validateShiftId(id);
            Shift s = checkShift(id);
            ShiftRepository.deleteShift(s);
        } catch (InvalidShiftIdException | ShiftNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
