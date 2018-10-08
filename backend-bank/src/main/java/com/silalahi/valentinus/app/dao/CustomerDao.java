package com.silalahi.valentinus.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.silalahi.valentinus.app.entity.Customer;

public interface CustomerDao extends PagingAndSortingRepository<Customer, String> {

}
