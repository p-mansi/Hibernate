package com.example.hibernate_example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.hibernate_example.Model.Laptop;
import com.example.hibernate_example.Model.Student;

import io.github.cdimascio.dotenv.Dotenv;

public class RelationshipExApp {

    public static void main(String[] args) throws IOException {

        int ch;

        Laptop laptop = new Laptop();
        Student student = new Student();

        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class)
                .addAnnotatedClass(Laptop.class);
        Dotenv dotenv = Dotenv.load();

        con.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
        con.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
        con.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Your Choice : 1. Insert | 2. View ");
        ch = Integer.parseInt(br.readLine());
        switch (ch) {
            case 1:
                Transaction tx = session.beginTransaction();
                laptop.setLid(101);
                laptop.setLname("Dell");

                student.setRollno(1);
                student.setName("Mansi");
                student.setMarks(70);
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
        sf.close();

    }
}