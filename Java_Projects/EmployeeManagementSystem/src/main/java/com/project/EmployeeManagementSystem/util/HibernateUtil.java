package com.project.EmployeeManagementSystem.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sf;
	static {
		try {
			sf= new Configuration().configure().buildSessionFactory();
			
		}catch(Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sf;
	}
}
