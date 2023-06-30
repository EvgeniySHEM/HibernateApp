package ru.sanctio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sanctio.model.Item;
import ru.sanctio.model.Person;

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
}
