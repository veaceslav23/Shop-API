package com.project.carrental.persistence.dao;

import com.project.carrental.persistence.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private SessionFactory sessionFactory;

    public UserEntity saveUser(UserEntity userEntity) throws HibernateException {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(userEntity);
        HibernateCriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        criteriaBuilder.
        transaction.commit();
        return userEntity;
    }
}
