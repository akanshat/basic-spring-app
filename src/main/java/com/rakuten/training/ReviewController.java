package com.rakuten.training;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ReviewService;

@RestController
public class ReviewController {
	
//	a. Retrieve all reviews for a given product
//	b. add a review to a product
	
	@Autowired
	ReviewService service;

	@GetMapping("/reviews/{product_id}")
	public List<Review> findReviewsByPid(@PathVariable("product_id") int pid) {
		return service.findByProduct_Id(pid);
	}
	
//	@PostMapping("/review/{pid}")
//	public ResponseEntity  addReviewToAProduct(@PathVariable("pid") int pid, @RequestBody Review r) {
//		service.addReviewToProduct(r, pid);
//	}
	
}
