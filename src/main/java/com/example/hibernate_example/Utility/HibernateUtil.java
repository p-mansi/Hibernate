package com.example.hibernate_example.Utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate_example.Model.Alien;
import com.example.hibernate_example.Model.AlienName;
import com.example.hibernate_example.Model.Laptop;
import com.example.hibernate_example.Model.Student;

import io.github.cdimascio.dotenv.Dotenv;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {

            // Configuration
            Configuration con = new Configuration().configure();
            con.addAnnotatedClass(Alien.class);
            con.addAnnotatedClass(AlienName.class);
            con.addAnnotatedClass(Laptop.class);
            con.addAnnotatedClass(Student.class);

            // env variable
            Dotenv dotenv = Dotenv.load();

            con.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
            con.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
            con.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));

            sessionFactory = con.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("SessionFactory Creation failed...");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("SessionFactory closed successfully");
        }
    }

}
