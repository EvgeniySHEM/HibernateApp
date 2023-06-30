package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sanctio.model.Person;

import java.util.List;

public class HQL {

    public static List<Person> getAllPerson(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

//            List<Person> people = session.createQuery("FROM Person", Person.class).getResultList();
            List<Person> people = session.createSelectionQuery("FROM Person", Person.class).getResultList();

            session.getTransaction().commit();

            return people;

        } finally {
            session.close();
        }
    }

    public static void updatePerson(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

//            session.createQuery("update Person set name='Update name' where age < 30").executeUpdate(); deprecated
            session.createMutationQuery("update Person set name='Update name' where age < 30").executeUpdate();

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public static void deletePerson(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            session.createMutationQuery("delete Person where age < 30").executeUpdate();

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
}
