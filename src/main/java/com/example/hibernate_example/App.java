package com.example.hibernate_example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Alien obj = new Alien();
        // obj.setAid(103);
        // obj.setAname("Drashti");
        // obj.setColor("Blue");

        Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);

        // ServiceRegistry reg = new
        // StandardServiceRegistryBuilder().applySetting(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory();

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        // session.save(obj);

        Obj = (Alien) session.get(Alien.class, 101);

        tx.commit();

        System.out.println("Saved Successfully!");
        System.out.println(obj);
    }
}
