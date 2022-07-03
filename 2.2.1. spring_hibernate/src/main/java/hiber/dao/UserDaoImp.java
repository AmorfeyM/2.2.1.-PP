package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @SuppressWarnings("unchecked")
   public User getUserWithCar(String model, int series) {
//      String hql = String.format("from User where Car.model = {0} and Car.series = {1}", model, series);
//      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);

      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User user where user.car.model = :paramName1 and user.car.series = :paramName2");
      query.setParameter("paramName1", model);
      query.setParameter("paramName2", series);
      return query.getSingleResult();
   }

}
