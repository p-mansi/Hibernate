package com.example.hibernate_example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.hibernate_example.Model.Laptop;
import com.example.hibernate_example.Model.Student;

public class RelationshipExApp {

    public static void main(String[] args) {

        Laptop laptop = new Laptop();
        laptop.setLid(101);
        laptop.setLname("Dell");

        Student student = new Student();
        student.setRollno(1);
        student.setName("Mansi");
        student.setMarks(60);

        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class)
                .addAnnotatedClass(Laptop.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.save(laptop);
        session.save(student);

        tx.commit();
        System.out.println("Saved Successfully");

    }
}