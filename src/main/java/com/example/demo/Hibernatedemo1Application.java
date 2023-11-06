package com.example.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Person;

@SpringBootApplication
public class Hibernatedemo1Application {

	public static void main(String[] args) {
		//SpringApplication.run(Hibernatedemo1Application.class, args);
		
		
		Configuration configuration=new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Person.class);
		
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		// create and save new person
		Person person=new Person();
		person.setName("Rohith");
		person.setDob(new Date());
		
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();
		System.out.println("Person Saved!");
		
		
		// create and save another person object
		Person person2=new Person();
		person2.setName("Karthik");
		person2.setDob(new Date());
		
		session.beginTransaction();
		session.persist(person2);
		session.getTransaction().commit();
		
		
		// merge the person object
		
		Person person3=new Person();
		person3.setName("Prabhas");
		person3.setDob(new Date());
		
		session.beginTransaction();
		session.merge(person3);
		session.getTransaction().commit();
		
		// save or update
		Person person4=new Person();
		person4.setName("RamCharan");
		person4.setDob(new Date());
		
		session.beginTransaction();
		session.saveOrUpdate(person4);
		session.getTransaction().commit();
		
		
		// Update person object
		session.beginTransaction();
		person.setName("Rahul");
		session.merge(person);
		session.getTransaction().commit();
		System.out.println("Person updated!");
		
		// Retrive person object
		Person retrive=session.get(Person.class, person.getId());
		if(retrive!=null) {
			System.out.println("Retrieved Person Details:");
			System.out.println("ID: " + retrive.getId());
            System.out.println("Name: " + retrive.getName());
            System.out.println("DOb:"+ retrive.getDob());
		}
		else {
			System.out.println("Person not found");
		}
		
		// Delete person object
		
		session.beginTransaction();
		session.delete(retrive);
		session.getTransaction().commit();
		System.out.println("Person deleted successfully");
		
		session.close();
	}

}
