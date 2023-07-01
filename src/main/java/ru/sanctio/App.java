package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sanctio.model.*;

import static ru.sanctio.Methods.*;
import static ru.sanctio.HQL.*;
import static ru.sanctio.CheckingTheConnectionOfEntities.*;
import static ru.sanctio.OneToOne.*;
import static ru.sanctio.ManyToMany.*;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class).addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        addActorsForMovie(sessionFactory);
    }
}
