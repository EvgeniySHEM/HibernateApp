package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sanctio.model.Person;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.getCurrentSession();

//        addNewPerson(sessionFactory);
        findAndGet(sessionFactory);

    }

    private static void findAndGet(SessionFactory sessionFactory) {
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

    private static void addNewPerson(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person1 = new Person("Test1", 10);
            Person person2 = new Person("Test2", 20);
            Person person3 = new Person("Test3", 30);

            session.persist(person1);
            session.persist(person2);
            session.persist(person3);

            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
}
