package com.example.hibernate_example;

import com.example.hibernate_example.Model.Alien;
import com.example.hibernate_example.Utility.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

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

        // it will fired the query 2 time bcz we use defferent session for same
        // query if we are not using cacheable

        Session session2 = HibernateUtil.getSessionFactory().openSession();
        session2.beginTransaction();
        a = (Alien) session2.get(Alien.class, 101);
        System.out.println(a);
        session2.getTransaction().commit();
        session2.close();

        // ----------------------------------------------------------------------------
        // Also For Query Cache is working
        // ----------------------------------------------------------------------------

        Session session3 = HibernateUtil.getSessionFactory().openSession();
        session3.beginTransaction();
        Query<Alien> q1 = session3.createQuery("from Alien where aid=101", Alien.class);
        q1.setCacheable(true);
        a = q1.uniqueResult();
        System.out.println(a);
        session3.getTransaction().commit();
        session3.close();

        // it will fired the query 2 time bcz we use defferent session for same
        // query if we are not using cacheable

        Session session4 = HibernateUtil.getSessionFactory().openSession();
        session4.beginTransaction();
        a = (Alien) session4.get(Alien.class, 101);
        System.out.println(a);

        session4.getTransaction().commit();
        session4.close();
        // ----------------------------------------------------------------------------

        HibernateUtil.shutdown();

    }

}
