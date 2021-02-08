package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.Query;
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
//		Query q = em.createQuery("SELECT r FROM Review r WHERE r.product.id=" + pid);
//		return q.getResultList();

		Query q = em.createQuery("SELECT r FROM Review r WHERE r.product.id=:productId");
		q.setParameter("productId", pid);
		return q.getResultList();
	}

	public void deleteByProduct_id(int pid) {
		Query q = em.createQuery("DELETE FROM Review r WHERE r.product.id=:productId");
		q.setParameter("productId", pid);
		q.executeUpdate();
	}

}
