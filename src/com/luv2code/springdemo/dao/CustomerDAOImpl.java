package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //need inject session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {


        //get the current hib session
        Session currentSession =
                sessionFactory.getCurrentSession();
        //create query
        Query<Customer> theQuery=currentSession.createQuery("from Customer  order by lastName",
                Customer.class);
        //execute query and get rezult list
        List<Customer> customers=theQuery.getResultList();
        //return the rezult


        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        // get current hibernate session
        Session session=sessionFactory.getCurrentSession();
        //save or update customer
        session.saveOrUpdate(theCustomer);

    }

    @Override
    public Customer getCustomer(int theId) {

        Session session=sessionFactory.getCurrentSession();
        //get customer

        return (Customer) session.get(Customer.class,theId);
    }

    @Override
    public void deleteCustomer(int theId) {
        Session session=sessionFactory.getCurrentSession();
        //delete object with primary key
        Query theQuery=
                session.createQuery(
                        "delete from Customer where id=:customerId");
        theQuery.setParameter("customerId",theId);
        theQuery.executeUpdate();
    }


}
