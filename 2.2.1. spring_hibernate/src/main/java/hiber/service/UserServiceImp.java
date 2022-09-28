package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
   private User user;
   private UserDao userDao;

   public UserServiceImp(User user, UserDao userDao) {
      this.user = user;
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> allUsers() {
      return userDao.allUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User getOwner(String model, int series) {
      return userDao.getOwner(model, series);
   }

}
