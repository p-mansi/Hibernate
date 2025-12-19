package com.example.hibernate_example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.example.hibernate_example.Model.Alien;
import com.example.hibernate_example.Model.AlienName;

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

        an.setFname("Mansi");
        an.setLname("Padia");
        an.setMname("Gopalbhai");

        obj.setAid(101);
        obj.setAname(an);
        obj.setColor("Cream");

        Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);

        // ServiceRegistry reg = new
        // StandardServiceRegistryBuilder().applySetting(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory();

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        session.save(obj);

        obj = (Alien) session.get(Alien.class, 101);

        tx.commit();

        System.out.println("Saved Successfully!");
        System.out.println(obj);
    }
}
