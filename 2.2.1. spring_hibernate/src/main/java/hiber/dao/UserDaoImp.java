package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private SessionFactory sessionFactory;
   private User user;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory, User user) {
      this.sessionFactory = sessionFactory;
      this.user = user;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> allUsers() {
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
