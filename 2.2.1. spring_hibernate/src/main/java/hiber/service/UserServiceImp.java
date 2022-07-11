package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   public User getUserByCar(Car car) {
      return userDao.getUserByCar(car);
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByCarModelAndCarSeries(String model, Integer series, CarService carService) {
      Car car = carService.getCarByModelAndSeries(model, series);
      return userDao.getUserByCar(car);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

}
