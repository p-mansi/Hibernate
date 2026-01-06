package com.example.hibernate_example;

import org.hibernate.Session;

import com.example.hibernate_example.Model.Laptop;
import com.example.hibernate_example.Utility.HibernateUtil;

public class GetVsLoadExApp {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // get fires query all the time weather you want to use it or not
        Laptop laptop = session.get(Laptop.class, 101);
        System.out.println(laptop.getLid() + " " + laptop.getLname());

        // Hibernate 6 remove load method we can use
        // load method fire query when you want to use then it goes to database
        Laptop laptop2 = session.getReference(Laptop.class, 102);
        System.out.println(laptop2.getLname());

        session.getTransaction().commit();
        HibernateUtil.shutdown();
        session.close();

    }
}
