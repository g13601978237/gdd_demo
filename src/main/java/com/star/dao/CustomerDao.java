package com.star.dao;

import com.star.entity.Customer;
import com.star.entity.CustomerExample;
import org.springframework.stereotype.Repository;

/**
 * CustomerDao继承基类
 */

@Repository
public interface CustomerDao extends MyBatisBaseDao<Customer, Integer, CustomerExample> {
}