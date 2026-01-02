package com.example.hibernate_example;

import com.example.hibernate_example.Model.Alien;
import com.example.hibernate_example.Utility.HibernateUtil;

import org.hibernate.Session;

public class CacheExApp {

    public static void main(String[] args) {

        Alien a = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        // ----------------------------------------------------------------------------
        // Here query is fired only once bcz it uses First Level Cache

        a = (Alien) session.get(Alien.class, 101);
        System.out.println(a);

        session.getTransaction().commit();
        session.close();
        // ----------------------------------------------------------------------------

        // Here it will fired the query 2 time bcz we use defferent session for same
        // query
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        session2.beginTransaction();
        a = (Alien) session2.get(Alien.class, 101);
        System.out.println(a);

        // ----------------------------------------------------------------------------

        session2.getTransaction().commit();
        session2.close();
        HibernateUtil.shutdown();

    }

}
