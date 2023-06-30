package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sanctio.model.Person;

public class Methods {

    public static void findAndGet(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person1 = session.find(Person.class, 1); //JPA
            if (person1 != null)
                System.out.println(person1.getName() + " " + person1.getAge());

            Person person = session.get(Person.class, 1); //Hibernate
            if (person != null)
                System.out.println(person.getName() + " " + person.getAge());

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public static void addNewPerson(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person1 = new Person("Test1", 10);
            Person person2 = new Person("Test2", 20);
            Person person3 = new Person("Test3", 30);
            Person person4 = new Person("Test4", 40);
            Person person5 = new Person("Test5", 50);

            session.persist(person1);
            session.persist(person2);
            session.persist(person3);
            session.save(person4);
            session.save(person5);

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public static int addNewPersonAndGetId(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person1 = new Person("Some person2", 10);
            session.persist(person1);

            session.getTransaction().commit();

            return person1.getId();

        } finally {
            session.close();
        }
    }

    public static void update(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person1 = session.find(Person.class, 1); //JPA
            if (person1 != null)
                person1.setName("New test name");

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    public static void delete(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person1 = session.find(Person.class, 1); //JPA
            if (person1 != null)
                session.remove(person1);

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
}
