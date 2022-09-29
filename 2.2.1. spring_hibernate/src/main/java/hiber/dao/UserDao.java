package hiber.dao;

import hiber.model.User;
import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> getAllUsers();
   User getOwner(String model, int series);
}
