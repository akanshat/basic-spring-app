package com.rakuten.training.web;

import org.springframework.web.client.RestTemplate;

import com.rakuten.training.domain.Product;

class ProductQATest {

	public static void main(String[] args) {
		// this will work when our entire spring container is working, this wont be a
		// unit test
		RestTemplate template = new RestTemplate();
		Product p = template.getForObject("http://localhost:8080/products/3", Product.class);

		if (p.getId() == 3)
			System.out.println("Test succeeds!!");
		else
			System.out.println("test fails");
	}
}
