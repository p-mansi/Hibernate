package com.example.hibernate_example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.hibernate_example.Model.Laptop;
import com.example.hibernate_example.Model.Student;

import io.github.cdimascio.dotenv.Dotenv;

public class RelationshipExApp {

    public static void main(String[] args) {

        Laptop laptop = new Laptop();
        laptop.setLid(101);
        laptop.setLname("Dell");

        Student student = new Student();
        student.setRollno(1);
        student.setName("Mansi");
        student.setMarks(70);
        student.getLaptop().add(laptop);

        laptop.getStudent().add(student);

        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class)
                .addAnnotatedClass(Laptop.class);
        Dotenv dotenv = Dotenv.load();

        con.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
        con.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
        con.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(laptop);
        session.persist(student);

        tx.commit();
        session.close();
        sf.close();
        System.out.println("Saved Successfully");

    }
}