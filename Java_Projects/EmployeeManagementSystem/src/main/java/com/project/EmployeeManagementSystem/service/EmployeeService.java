package com.project.EmployeeManagementSystem.service;

import java.util.List;
import java.util.Scanner;

import com.project.EmployeeManagementSystem.enums.Position;
import com.project.EmployeeManagementSystem.exception.*;
import com.project.EmployeeManagementSystem.model.Employee;
import com.project.EmployeeManagementSystem.repository.EmployeeRepository;

public class EmployeeService {
	static Scanner sc= new Scanner(System.in);
    private static void validateEmployeeId(String employeeId) throws InvalidEmployeeIdException {
        if (employeeId == null || employeeId.isEmpty()) {
            throw new InvalidEmployeeIdException("Employee ID cannot be null or empty");
        }
    }
    private static void validateEmail(String email) throws InvalidEmailFormatException {
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidEmailFormatException("Enter a valid email address");
        }
    }

    private static void validatePhoneNumber(String phone) throws InvalidPhoneNumberException {
        if (phone.length() != 10) {
            throw new InvalidPhoneNumberException("Phone number should be 10 digits long");
        }
    }

    private static void validatePosition(String position) throws InvalidPositionException {
        if (!position.toUpperCase().matches("MANAGER|DEVELOPER|TESTER|HR")) { 
            throw new InvalidPositionException("Invalid position title: " + position);
        }
    }

    private static Employee checkEmployeeExists(int employeeId) throws EmployeeNotFoundException {
        Employee emp = EmployeeRepository.employeeExists(employeeId);
    	if (emp==null) {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
        }else return emp;
    }

    public static void addEmployee(){
        String name, email, phone, position;
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Email: ");
        email = sc.nextLine();
        System.out.print("Enter Phone: ");
        phone = sc.nextLine();
        System.out.print("Enter Position(manager|developer|tester|hr): ");
        position = sc.nextLine();

        try {

            validateEmail(email);
            validatePosition(position);

            EmployeeRepository.addEmployee(new Employee(name, email, phone,
            		Position.valueOf(position.toUpperCase())));
        } catch (IllegalArgumentException | InvalidEmailFormatException | InvalidPositionException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void viewEmployeeDetails(){
        List<Employee> emp=EmployeeRepository.viewEmployeeDetails();
        System.out.printf("%-15s %-20s %-25s %-15s %-20s%n",
                "Employee Id", "Name", "Email", "Phone", "Position");
        System.out.println("----------------------------------------------------------------------------");
        for(Employee e:emp) {
        	System.out.printf("%-15s %-20s %-25s %-15s %-20s%n",
                    e.getId(), e.getName(), e.getEmail(), e.getPhone(), e.getPosition().toString());
            
        }
        System.out.println("----------------------------------------------------------------------------");
    }
    public static void updateEmployeeInfo() {
        System.out.print("Enter employee ID for update: ");
        String id = sc.nextLine();

        String name, email, phone, position;
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Email: ");
        email = sc.nextLine();
        System.out.print("Enter Phone: ");
        phone = sc.nextLine();
        System.out.print("Enter Position(manager|developer|tester|hr): ");
        position = sc.nextLine();

        try {
            validateEmployeeId(id);
            validateEmail(email);
            validatePhoneNumber(phone);
            validatePosition(position);
            checkEmployeeExists(Integer.parseInt(id));

            EmployeeRepository.updateEmployeeInfo(new Employee(name, email, phone,
            		Position.valueOf(position.toUpperCase())),Integer.parseInt(id));
        } catch (IllegalArgumentException | InvalidEmployeeIdException | InvalidEmailFormatException |
                 InvalidPhoneNumberException | InvalidPositionException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteEmployee(){
        System.out.print("Enter employee Id for delete: ");
        int id= Integer.parseInt(sc.nextLine());
        Employee emp;
		try {
			emp = checkEmployeeExists(id);
			EmployeeRepository.deleteEmployee(emp);
		} catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
		}
    }
}
