package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sanctio.model.Person;

public class App
{
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person1 = session.find(Person.class, 1); //JPA
            System.out.println(person1.getName() + " " + person1.getAge());

            Person person = session.get(Person.class, 1); //Hibernate
            System.out.println(person.getName() + " " + person.getAge());

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }

    }
}
