package com.project.EmployeeManagementSystem.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.EmployeeManagementSystem.model.Employee;
import com.project.EmployeeManagementSystem.util.HibernateUtil;

public class EmployeeRepository {

	public static Employee employeeExists(int id) {
		Transaction transaction=null;
    	try{
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		transaction= session.beginTransaction();
    		Employee emp=session.get(Employee.class,id);
    		if(emp!=null) return emp;
    		transaction.commit();
    	}catch(Exception ex) {
    		if(transaction!=null) transaction.rollback();
    		ex.printStackTrace();
    	}
		return null;
	}
    public static void addEmployee(Employee e){
    	Transaction transaction=null;
    	try{
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		transaction= session.beginTransaction();
    		session.persist(e);
    		transaction.commit();
    	}catch(Exception ex) {
    		if(transaction!=null) transaction.rollback();
    		ex.printStackTrace();
    	}

    }
    public static List<Employee> viewEmployeeDetails() {
    	List<Employee> employees=null;
    	Transaction transaction=null;
    	try{
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		transaction= session.beginTransaction();
    		String hql= "from Employee";
    	    Query<Employee> query= session.createQuery(hql, Employee.class);
    	    employees= query.getResultList();
    		
    		transaction.commit();
    	}catch(Exception ex) {
    		if(transaction!=null) transaction.rollback();
    		ex.printStackTrace();
    	}
    	return employees;

    }

    public static void updateEmployeeInfo(Employee e,int id){
    	Transaction transaction=null;
    	try{
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		transaction= session.beginTransaction();
    		Employee emp =session.get(Employee.class, id);
    		if(emp!=null){
    			emp.setName(e.getName());
    			emp.setEmail(e.getEmail());
    			emp.setPhone(e.getPhone());
    			emp.setPosition(e.getPosition());
    		}
    		transaction.commit();
    	}catch(Exception ex) {
    		if(transaction!=null) transaction.rollback();
    		ex.printStackTrace();
    	}
        
    }
    public static void deleteEmployee(Employee e){
    	Transaction transaction=null;
    	try {
    		Session session= HibernateUtil.getSessionFactory().openSession();
    		transaction= session.beginTransaction();
    		session.remove(e);
    		transaction.commit();
    	}catch(Exception ex) {
    		if(transaction!=null) transaction.rollback();
    		ex.printStackTrace();
    	}
    }
}
