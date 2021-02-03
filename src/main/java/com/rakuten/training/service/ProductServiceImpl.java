package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

public class ProductServiceImpl implements ProductService {

	ProductDAO dao; // null now
	// = new ProductDAOInMemImpl();

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
		if (existing.getPrice() * existing.getQoh() >= 10000) {
			throw new IllegalStateException("Can't delete a Product when stock >= 10,000");
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
