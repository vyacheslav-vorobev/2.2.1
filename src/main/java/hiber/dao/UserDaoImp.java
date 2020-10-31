package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      Session session = sessionFactory.getCurrentSession();
              session.save(user);
      if(user.getCar() != null) {
         session.save(user.getCar());
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getCarOwner(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      try {
         Car car = (Car)session.createQuery("FROM Car where (model, series) = (:model, :series)")
                 .setParameter("model", model).setParameter("series", series).getSingleResult();
         return (User)session.createQuery("FROM User where car_id = :carId")
                 .setParameter("carId", car.getId()).getSingleResult();
      } catch (NoResultException e) {
         System.out.println("model:" + model +" series:"+ series +
                 " Такого автомобиля нет или юзера с таким автомобилем нет");
         return null;
      }
   }

}
