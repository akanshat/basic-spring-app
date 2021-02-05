package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rakuten.training.domain.Review;

@Repository
@Transactional
public class ReviewDAOJpaImpl implements ReviewDAO {

	@Autowired
	EntityManager em;

	@Override
	public Review save(Review r) {
		em.persist(r);
		return r;
	}

	@Override
	public Review findById(int id) {
		return em.find(Review.class, id);
	}

	@Override
	public List<Review> findByProduct_Id(int pid) {
		return null;
	}

}
