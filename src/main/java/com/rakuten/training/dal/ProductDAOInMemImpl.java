package com.rakuten.training.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.rakuten.training.domain.Product;

@Repository
public class ProductDAOInMemImpl implements ProductDAO {

	// why is this not static?, for each new object we need it to create a new
	// Implementation
	// db is single for one object, that object will get, put, delete
//	Map<Integer, Product> db = new HashMap<>(); // not thread safe
	Map<Integer, Product> db = new ConcurrentHashMap<>(); // thread safe
//	int idSequence = 0; // not thread safe, for this we will make a thread safe alternative to this integer
	AtomicInteger idSequence = new AtomicInteger(0);
	// we cannot make everything critical section, in java we have a concurrent
	// hashmap

	@Override
	public Product save(Product toBeSaved) {
//		int id = ++idSequence; // not thread safe
		int id = idSequence.incrementAndGet(); // thread safe, atomic increment method used
		toBeSaved.setId(id);
		db.put(id, toBeSaved);
		return toBeSaved;
	}

	@Override
	public Product findById(int id) {
		return db.get(id);
	}

	@Override
	public List<Product> findAll() {
		return new ArrayList<>(db.values());
	}

	@Override
	public void deleteById(int id) {
		db.remove(id);
	}

}
