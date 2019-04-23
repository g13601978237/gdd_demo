package com.star.service.impl;

import com.star.dao.CustomerDao;
import com.star.entity.Customer;
import com.star.entity.CustomerExample;
import com.star.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private  CustomerDao customerDao;


    @Override
    public Customer getByTelno(long telno) {
        CustomerExample example = new CustomerExample();
        example.createCriteria().andCustTelnoEqualTo(telno);
        List<Customer> customers = customerDao.selectByExample(example);
        if (customers == null || customers.size() == 0) {
            System.out.println("customersisnull");
            return null;
        } else {
            Customer customer1 = customers.get(0);
            System.out.println("CustomerService:customers"+customer1);
            return customer1;
        }
    }

    @Override
    public Customer getByCustId(int id) {
        Customer customer = customerDao.selectByPrimaryKey(id);
        return customer;
    }

    @Override
    public boolean save(Customer customer) {
        System.out.println("customerDao.insert(customer)" );
        return customerDao.insert(customer)>0;
    }
}
