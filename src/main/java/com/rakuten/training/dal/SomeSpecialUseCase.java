package com.rakuten.training.dal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SomeSpecialUseCase {
	@Autowired
	@Qualifier("productDAOInMemImpl")
	ProductDAO dao;

	@PostConstruct
	public void playWithDAO() {
		System.out.println("<<<<<<<<<<<<<<<<<typeof dao in SomeSpecialUseCase: " + dao.getClass().getName());
	}
}
