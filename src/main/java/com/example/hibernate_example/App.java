package com.example.hibernate_example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.example.hibernate_example.Model.Alien;
import com.example.hibernate_example.Model.AlienName;

import io.github.cdimascio.dotenv.Dotenv;

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
        Dotenv dotenv = Dotenv.load();

        con.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
        con.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
        con.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));

        // ServiceRegistry reg = new
        // StandardServiceRegistryBuilder().applySetting(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory();

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        session.persist(obj);

        obj = (Alien) session.get(Alien.class, 101);

        tx.commit();
        session.close();
        sf.close();

        System.out.println("Saved Successfully!");
        System.out.println(obj);
    }
}
