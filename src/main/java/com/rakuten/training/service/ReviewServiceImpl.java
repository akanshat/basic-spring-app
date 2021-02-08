package com.rakuten.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDAO reviewDAO;

	@Autowired
	ProductDAO productDAO;

	@Override
	public Review addReviewToProduct(Review r, int productId) {

		Product p = productDAO.findById(productId);
		r.setProduct(p);
		return reviewDAO.save(r);
		// if such an product doesnt exist, we will try to create that product here

//		Product p = productDAO.findById(productId);
//		if (p != null) {
//			r.setProduct(p);
//			return reviewDAO.save(r);
//		} else {
//			// we did this separately
//			Product newProduct = new Product("temp", 1000, 10);
////			productDAO.save(newProduct); // as we added cascade= persist in review domain
//			r.setProduct(newProduct);
//			reviewDAO.save(r);
//			return r;
//		}
	}

	@Override
	public Review findById(int id) {
		Review r = reviewDAO.findById(id); // it shouldnot only retrieve this record but also the contained records
		// should every contained entity be loaded into memory?
		
		return r;
	}

	@Override
	public List<Review> findByProduct_Id(int pid) {
		return reviewDAO.findByProduct_Id(pid);
	}
	
	public void deleteReviewByPid(int pid) {
		reviewDAO.deleteByProduct_id(pid);
	}
}
