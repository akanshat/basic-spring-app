package com.rakuten.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ProductDAOInMemImpl;
import com.rakuten.training.dal.ProductDAOJpaImpl;
import com.rakuten.training.domain.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	// ProductServiceImpl is thread safe, provided this dao obj is thread safe
	// dao isnt thread safe now
	ProductDAO dao; // null now
	// = new ProductDAOInMemImpl();

//	private ProductDAO dao = new ProductDAOInMemImpl();

	@Autowired
	public void setDao(ProductDAO dao) {
		this.dao = dao;
	}

	@Override
	public int createNewProduct(Product toBeCreated) {
		if (toBeCreated.getPrice() * toBeCreated.getQoh() >= 10000) {
			Product created = dao.save(toBeCreated);
			return created.getId();
		} else {
			throw new IllegalArgumentException("The product passed to create is not worth 10000");
		}
	}

	@Override
	public void removeExisting(int id) {
		Product existing = dao.findById(id);
		if (existing == null) {
			throw new IllegalArgumentException("product with id " + id + " doesn't exist!");
		}
		if (existing.getPrice() * existing.getQoh() >= 100000) {
			throw new IllegalStateException("Can't delete a Product when stock >= 100,000");
		}

		dao.deleteById(id);
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Product findById(int id) {
		return dao.findById(id);
	}

}
