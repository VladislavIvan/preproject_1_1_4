package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory mySessionFactory;
    public UserDaoHibernateImpl(SessionFactory mySessionFactory) {

        this.mySessionFactory = mySessionFactory;
    }


    @Override
    public void createUsersTable() {
        String sqlDltTable = """
                CREATE TABLE IF NOT EXISTS users (
                \t  id INTEGER auto_increment primary KEY ,
                    name varchar(20),
                    lastName varchar(20),
                    age integer(3));""";
        try (Session session = mySessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlDltTable).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlDropTblUsrs = "DROP TABLE IF EXISTS users";
        try (Session session = mySessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlDropTblUsrs).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> saveUser(String name, String lastName, byte age) {
        try (Session session = mySessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = mySessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = mySessionFactory.openSession()) {
            session.beginTransaction();
            userList = session.createQuery("FROM User").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = mySessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
