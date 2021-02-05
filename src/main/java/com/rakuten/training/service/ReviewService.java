package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.domain.Review;

public interface ReviewService {

	Review addReviewToProduct(Review r, int productId);

	Review findById(int id);

	List<Review> findByProduct_Id(int pid);

}