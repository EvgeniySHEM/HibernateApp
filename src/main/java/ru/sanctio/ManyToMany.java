package ru.sanctio;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sanctio.model.Actor;
import ru.sanctio.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class ManyToMany {

    public static void addNewMovieAndActors(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Movie movie = new Movie("Pulp fiction", 1994);
            Actor actor1 = new Actor("Harvey Keitel", 81);
            Actor actor2 = new Actor("Samuel L. Jackson", 72);

            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
            actor1.setMovies(new ArrayList<>(List.of(movie)));
            actor2.setMovies(new ArrayList<>(List.of(movie)));

            session.persist(movie);
            session.persist(actor1);
            session.persist(actor2);

            session.getTransaction().commit();
        }
    }

    public static List<Actor> getActorsForMovie(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Movie movie = session.get(Movie.class, 1);
            Hibernate.initialize(movie.getActors()); //иначе вызов может не произойти и Lazy объекты не подгрузятся

            session.getTransaction().commit();
            return movie.getActors();
        }
    }

    public static void deleteActorsForMovie(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Actor actor = session.find(Actor.class, 1);
            Movie movie = actor.getMovies().get(0);

            actor.getMovies().remove(0);
            movie.getActors().remove(actor);

            session.getTransaction().commit();
        }
    }

    public static void addActorsForMovie(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Actor actor = session.find(Actor.class, 1);
            Movie movie = session.find(Movie.class, 1);

            actor.getMovies().add(movie);
            movie.getActors().add(actor);

            session.getTransaction().commit();
        }
    }
}
