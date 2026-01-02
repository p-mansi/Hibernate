package com.example.hibernate_example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.hibernate_example.Model.Alien;
import com.example.hibernate_example.Model.AlienName;
import com.example.hibernate_example.Utility.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Alien obj = new Alien();
        // Alien obj = null; // for fetching data bcz we don't need to create object for
        // fetching

        AlienName an = new AlienName();

        an.setFname("Siddharth");
        an.setLname("Padia");
        an.setMname("Gopalbhai");

        obj.setAid(103);
        obj.setAname(an);
        obj.setColor("Blue");

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        session.persist(obj);

        obj = (Alien) session.get(Alien.class, 103);

        tx.commit();

        System.out.println("Saved Successfully!");
        System.out.println(obj);

        session.close();
        HibernateUtil.shutdown();

    }
}
