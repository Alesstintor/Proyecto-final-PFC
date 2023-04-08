package com.ales.fittrack.Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class AccesDB {

    private static StandardServiceRegistry sr;
    private static SessionFactory sf;
    private static Session session;
    private static boolean opened;



    public static void save(Object obj){
        session.beginTransaction();

        session.persist(obj);

        session.getTransaction().commit();

    }

    public static void saveAll(List<Object> objectList) {
        session.beginTransaction();

        objectList.forEach(obj -> session.persist(obj));

        session.getTransaction().commit();

    }

    public static void init() {
        sr = new StandardServiceRegistryBuilder().configure().build();
        sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
        session = sf.openSession();
        opened = true;

    }

    public static void close() {
        session.close();
        sf.close();
        sr.close();
    }

    public static boolean isOpen() {
        return opened;
    }




}
