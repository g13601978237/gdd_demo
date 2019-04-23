package com.star.service;

import com.star.entity.Customer;

public interface CustomerService {
     Customer getByTelno(long telno);

     Customer getByCustId(int id);

     boolean save(Customer customer);
}
