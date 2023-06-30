package ru.sanctio;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sanctio.model.Person;

import static ru.sanctio.Methods.*;
import static ru.sanctio.HQL.*;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        deletePerson(sessionFactory);
        System.out.println(getAllPerson(sessionFactory));
    }
}
