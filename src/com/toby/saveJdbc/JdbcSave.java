package com.toby.saveJdbc;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.toby.model.Student;


public class JdbcSave {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();

		try {
			Student test1 = new Student("Ken","Chan","abc123@test.com");

			session.beginTransaction();
									
			List<Student> students = session.createQuery("from Student s Where s.lastName = 'Chan' OR s.email like '%gmail.com'").getResultList();
						
			if(students != null) {
				for(Student student : students) {
					System.out.println(student.toString());
				}
			}
			
			session.getTransaction().commit();			
			
		}finally {
			sessionFactory.close();
		}
	
	}

}
