package com.silalahi.valentinus.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.silalahi.valentinus.app.entity.Account;

public interface AccountDao extends PagingAndSortingRepository<Account, String> {

}
