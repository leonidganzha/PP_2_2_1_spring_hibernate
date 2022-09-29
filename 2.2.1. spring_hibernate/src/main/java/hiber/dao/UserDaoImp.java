package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getAllUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getOwner(String model, int series) {
      Query query = sessionFactory
              .getCurrentSession()
              .createQuery("""
                                 from User user
                                 where user.userCar.model = :paramM
                                 and user.userCar.series = :paramS
                              """);
      query.setParameter("paramM", model);
      query.setParameter("paramS", series);
      return (User) query.getSingleResult();
   }
}
