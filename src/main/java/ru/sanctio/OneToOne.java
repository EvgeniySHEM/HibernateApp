package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sanctio.model.Passport;
import ru.sanctio.model.Person;

public class OneToOne {

    public static void addNewPersonAndPassport(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = new Person("Test add passport", 14);
            Passport passport = new Passport(123456);

            person.setPassport(passport);

            session.persist(person);

            session.getTransaction().commit();
        }
    }

    public static void addPassportForPerson(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.find(Person.class, 6);
            Passport passport = new Passport(735478);

            person.setPassport(passport);

            session.persist(passport);

            session.getTransaction().commit();
        }
    }

    public static void getPersonAndGetPassport(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.find(Person.class, 6);
            System.out.println(person.getPassport().getPassportNymber());
            System.out.println("=======================");
            Passport passport = session.find(Passport.class, 6);
            System.out.println(passport.getPerson().getName());

            session.getTransaction().commit();
        }
    }
}
