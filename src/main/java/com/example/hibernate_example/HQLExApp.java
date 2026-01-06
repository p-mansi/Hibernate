package com.example.hibernate_example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.example.hibernate_example.Model.Alien;
import com.example.hibernate_example.Utility.HibernateUtil;

public class HQLExApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Alien> q1 = session.createQuery("from Alien", Alien.class);
        List<Alien> aliens = q1.list();

        for (var a : aliens) {
            System.out.println(a.getAid());
            System.out.println(a.getColor());
            System.out.println(a.getAname());
        }
        session.getTransaction().commit();

        System.out.println("--------------------------------------------------------------------------");

        session.beginTransaction();

        q1 = session.createQuery("from Alien a where a.aname.fname='Mansi'", Alien.class);
        Alien a = (Alien) q1.uniqueResult();

        System.out.println(a);

        session.getTransaction().commit();

        System.out.println("--------------------------------------------------------------------------");

        session.beginTransaction();

        Query<Object[]> q2 = session.createQuery("select a.aid,a.color from Alien a where a.aname.fname='Mansi' ",
                Object[].class);
        Object[] obj = q2.uniqueResult();

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("id : " + obj[0] + ", color : " + obj[1]);
        System.out.println("--------------------------------------------------------------------------");

        session.getTransaction().commit();

        System.out.println("--------------------------------------------------------------------------");
        session.beginTransaction();

        session.getTransaction().commit();

        session.close();
        HibernateUtil.shutdown();
    }

}
