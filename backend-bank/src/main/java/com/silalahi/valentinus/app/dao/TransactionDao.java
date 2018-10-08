package com.silalahi.valentinus.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.silalahi.valentinus.app.entity.Transaction;

public interface TransactionDao extends PagingAndSortingRepository<Transaction, String> {

}
