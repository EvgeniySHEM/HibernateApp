package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sanctio.model.Item;
import ru.sanctio.model.Passport;
import ru.sanctio.model.Person;

import static ru.sanctio.Methods.*;
import static ru.sanctio.HQL.*;
import static ru.sanctio.CheckingTheConnectionOfEntities.*;
import static ru.sanctio.OneToOne.*;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class).addAnnotatedClass(Passport.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        getPersonAndGetPassport(sessionFactory);
    }
}
