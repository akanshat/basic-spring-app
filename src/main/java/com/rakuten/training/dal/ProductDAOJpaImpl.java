package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Product;

@Primary
@Repository
@Transactional
public class ProductDAOJpaImpl implements ProductDAO {

	@Autowired
	EntityManager em;
	// a bean autoconfigured by spring because of the @autowired anno.

	@Override
	public Product save(Product toBeSaved) {
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public Product findById(int id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
		Query q = em.createQuery("select p from Product as p");
		return q.getResultList();
	}

	@Override
	public void deleteById(int id) {
		Query q = em.createQuery("DELETE FROM Review r where r.product.id=:pid");
		q.setParameter("pid", id);
		int numReviewsDeleted = q.executeUpdate();
		System.out
				.println("<<<<<<Deleted " + numReviewsDeleted + "reviews before deleting with id " + id + ">>>>>>>>>>");
		Product p = em.find(Product.class, id);// getting a persistent object
		em.remove(p);
		// after remove state of p will be -> new/transient as it doesn't represent any
		// row and it is removed from the persistence context, (NOT detached)
	}

}
