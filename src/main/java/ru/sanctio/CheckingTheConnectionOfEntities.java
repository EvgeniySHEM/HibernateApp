package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sanctio.model.Item;
import ru.sanctio.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckingTheConnectionOfEntities {

    public static List<Item> getItemsForPerson(SessionFactory sessionFactory) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.get(Person.class, 3);
            List<Item> items = person.getItems();

            session.getTransaction().commit();
            return items;
        }
    }

    public static void addNewItemForPerson(SessionFactory sessionFactory) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = session.get(Person.class, 3);
            Item item = new Item(person, "Some item");
            person.getItems().add(item); // для того, чтобы и в кэше были точные данные

            session.persist(item);
            session.getTransaction().commit();
        }
    }

    public static void testCascading(SessionFactory sessionFactory) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Person person = new Person("Test cascading", 30);
            person.addItem(new Item("Item 1"));
            person.addItem(new Item("Item 2"));
            person.addItem(new Item("Item 3"));

            session.persist(person);

            session.getTransaction().commit();
        }
    }
}
