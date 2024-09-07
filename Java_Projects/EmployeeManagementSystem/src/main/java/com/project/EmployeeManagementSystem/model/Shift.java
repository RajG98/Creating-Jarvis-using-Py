package com.project.EmployeeManagementSystem.model;

import java.sql.Time;
import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity 
@Table(name="shift")
public class Shift {
	@Id
	@Column(name="shift_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@Temporal(TemporalType.DATE)
    private Date shift_date;
	@Temporal(TemporalType.TIME)
    private Time start_time;
	@Temporal(TemporalType.TIME)
    private Time end_time;
	
	public Shift() {
		
	}


    public int getId() {
		return id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getShift_date() {
        return shift_date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }


    public void setShift_date(Date shift_date) {
		this.shift_date = shift_date;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public Shift(Employee emp,Date shiftDate, Time start_time, Time end_time) {
		this.employee=emp;
        this.shift_date = shiftDate;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
