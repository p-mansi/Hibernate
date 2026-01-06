package com.example.hibernate_example;

import org.hibernate.Session;

import com.example.hibernate_example.Model.Alien;
import com.example.hibernate_example.Model.AlienName;
import com.example.hibernate_example.Utility.HibernateUtil;

public class HibernateObjStatesApp {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        AlienName anm = new AlienName();
        anm.setFname("Paul");
        anm.setLname("George");
        anm.setMname("Bala");

        Alien a = new Alien(); // Transient state
        a.setAid(104);
        a.setAname(anm);
        a.setColor("neon");

        session.persist(a); // Persistent state
        a.setColor("pink");
        session.getTransaction().commit();

        session.beginTransaction();
        session.remove(a); // Removed state
        session.getTransaction().commit();

        session.beginTransaction();
        session.detach(a); // Detached object will not affect the database unless it is re-attached
        a.setColor("sky"); // it will not affect the database

        HibernateUtil.shutdown();
        session.close();

    }
}
