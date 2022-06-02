package application.dao;

import application.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    @Qualifier(value = "entityManager")
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();
        return users;
    }

    @Override
    public void updateUser(Long id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUser(id));
    }

    @Override
    public User getUser(Long id) {
        return entityManager
                .createQuery("select u from User u where u.id=:paramId", User.class)
                .setParameter("paramId", id)
                .getSingleResult();
    }
}

