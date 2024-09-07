package com.project.EmployeeManagementSystem.model;


import com.project.EmployeeManagementSystem.enums.Position;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id")
	private int id;
	@Column(name="name",nullable = false)
	private String name;
	@Column(name="email",nullable = false,unique = true)
    private String email;
	@Column(name="phone")
    private String phone;
	@Column(name="position")
	@Enumerated(EnumType.STRING)
    private Position position;
	@OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
	private Shift shift;
	@OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
	private Salary salary;
	

    public Employee(String name, String email, String phone, Position position) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.position = position;
    }
    
    public Employee() {
		// TODO Auto-generated constructor stub
	}

    public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getName() {
        return name;
    }

    public Position getPosition() {
		return position;
	}

	public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    
    public int getId() {
		return id;
	}


}
