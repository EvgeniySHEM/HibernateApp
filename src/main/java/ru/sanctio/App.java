package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sanctio.model.Item;
import ru.sanctio.model.Person;

import static ru.sanctio.Methods.*;
import static ru.sanctio.HQL.*;
import static ru.sanctio.CheckingTheConnectionOfEntities.*;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        addNewItemForPerson(sessionFactory);
        System.out.println(getItemsForPerson(sessionFactory));
    }
}
