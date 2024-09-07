package com.project.EmployeeManagementSystem.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import com.project.EmployeeManagementSystem.model.Shift;
import com.project.EmployeeManagementSystem.util.HibernateUtil;

public class ShiftRepository {
	static Transaction transaction=null;
	public static Shift checkShift(int id) {
    	try{
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		transaction= session.beginTransaction();
    		Shift s=session.get(Shift.class,id);
    		if(s!=null) return s;
    		transaction.commit();
    	}catch(Exception ex) {
    		if(transaction!=null) transaction.rollback();
    		ex.printStackTrace();
    	}
		return null;
	}
	public static void addShift(Shift s){
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction= session.beginTransaction();
			session.persist(s);
			transaction.commit();
		}catch(Exception ex) {
			if(transaction!=null) transaction.rollback();
			ex.printStackTrace();
		}
		
    }
    public static List<Shift> getShiftDetails() {
    	List<Shift> shiftDetails=null;
    	try {
    		Session session = HibernateUtil.getSessionFactory().openSession();
			transaction= session.beginTransaction();
			String hql= "from Shift";
			Query<Shift> query= session.createQuery(hql, Shift.class);
			shiftDetails= query.getResultList();
			transaction.commit();
			
    	}catch(Exception ex) {
			if(transaction!=null) transaction.rollback();
    		ex.printStackTrace();
    	}
    	
        return shiftDetails;
    }

    public static void updateShift(Shift s, int id){
    	try {
    		Session session = HibernateUtil.getSessionFactory().openSession();
			transaction= session.beginTransaction();
			Shift shift = session.get(Shift.class, id);
			if(shift!=null) {
				shift.setEmployee(s.getEmployee());
				shift.setStart_time(s.getStart_time());
				shift.setEnd_time(s.getEnd_time());
				shift.setShift_date(s.getShift_date());
			}
			transaction.commit();
			
    	}catch(Exception ex) {
			if(transaction!=null) transaction.rollback();
    		ex.printStackTrace();
    	}
    	
        
    }
    public static void deleteShift(Shift s){
    	try {
    		Session session = HibernateUtil.getSessionFactory().openSession();
			transaction= session.beginTransaction();
			session.remove(s);
			transaction.commit();
			
    	}catch(Exception ex) {
			if(transaction!=null) transaction.rollback();
    		ex.printStackTrace();
    	}
    }
}
