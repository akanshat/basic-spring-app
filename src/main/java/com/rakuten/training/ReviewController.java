package com.rakuten.training;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@RestController
public class ReviewController {

//	a. Retrieve all reviews for a given product
//	b. add a review to a product

	@Autowired
	ReviewService service;

	@Autowired
	ProductService productSvc;

//	@GetMapping("/reviews")
//	public List<Review> findReviewsByPid(@RequestParam int pid) {
//		return service.findByProduct_Id(pid);
//	}

	@GetMapping("/products/{pid}/reviews")
	public ResponseEntity findReviewsByPid(@PathVariable("pid") int pid) {
		Product p = productSvc.findById(pid);
		if (p == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		{

			List<Review> reviews = service.findByProduct_Id(pid);
			return new ResponseEntity(reviews, HttpStatus.OK);
		}
	}

	@PostMapping("/products/{pid}/reviews")
	public ResponseEntity addReviewToAProduct(@PathVariable("pid") int pid, @RequestBody Review r) {

		Product p = productSvc.findById(pid);
		if (p != null) {
			Review reviewAdded = service.addReviewToProduct(r, pid);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products/" + pid + "/reviews" + reviewAdded.getId()));
			return new ResponseEntity<>(reviewAdded, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
