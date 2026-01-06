package com.example.hibernate_example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.hibernate_example.Model.Laptop;
import com.example.hibernate_example.Model.Student;
import com.example.hibernate_example.Utility.HibernateUtil;

public class RelationshipExApp {

    public static void main(String[] args) throws IOException {

        int ch;

        Laptop laptop = new Laptop();
        Student student = new Student();

        Session session = HibernateUtil.getSessionFactory().openSession();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Your Choice : 1. Insert | 2. View ");
        ch = Integer.parseInt(br.readLine());
        switch (ch) {
            case 1:
                Transaction tx = session.beginTransaction();
                laptop.setLid(102);
                laptop.setLname("Lenovo");

                student.setRollno(2);
                student.setName("Paul");
                student.setMarks(80);
                student.getLaptop().add(laptop);

                laptop.getStudent().add(student);

                session.persist(laptop);
                session.persist(student);

                tx.commit();
                System.out.println("Saved Successfully");
                break;
            case 2:
                System.out.println("Enter Student Id to fetch data : ");
                int id = Integer.parseInt(br.readLine());
                student = session.get(Student.class, id);

                if (student != null) {
                    System.out.println("Student ID   : " + student.getRollno());
                    System.out.println("Student Name : " + student.getName());
                    System.out.println("Marks        : " + student.getMarks());

                    System.out.println("Laptops : ");
                    for (Laptop l : student.getLaptop()) {
                        System.out.println("Laptop ID   : " + l.getLid());
                        System.out.println("Laptop Name : " + l.getLname());
                    }
                } else {
                    System.out.println("Data Not Found");
                }
                break;
            default:
                break;
        }

        session.close();
        HibernateUtil.shutdown();

    }
}