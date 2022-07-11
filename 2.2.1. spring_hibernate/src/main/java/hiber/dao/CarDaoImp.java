package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   public Car getCarByModelAndSeries(String model, int series) {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car car where car.model=:model and car.series=:series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      List<Car> results = query.getResultList();
      return results.size() > 0 ? results.get(0) : null;
   }
}
