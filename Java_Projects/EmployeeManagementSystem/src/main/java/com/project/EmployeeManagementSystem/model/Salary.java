package com.project.EmployeeManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="salary")
public class Salary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "salary_id")
	private int id;
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@Column(name = "salary_amount", nullable = false)
    private double salary_amount;
	@Column(name = "payment_date",nullable = false)
    private int payment_date;

    public Salary( double salary_amount, int payment_date) {
        this.salary_amount = salary_amount;
        this.payment_date = payment_date;
    }


    public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public double getSalary_amount() {
        return salary_amount;
    }

    public int getPayment_date() {
        return payment_date;
    }
}
