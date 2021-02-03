package com.rakuten.training.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

@Component
public class ProductConsoleUI {
	ProductService service;

	// = new ProductServiceImpl();
	// make this "ProductService service" injectible
	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}

	public void createProductWithUI() {
		try (Scanner kb = new Scanner(System.in)) {
			// Scanner is thread safe or not that doesnt matter, but System.in is thread
			// safe
			// each thread gets its own copy of scanner
			System.out.println("Enter a name: ");
			String name = kb.nextLine();
			System.out.println("Enter a price: ");
			float price = Float.parseFloat(kb.nextLine());
			System.out.println("Enter a QoH: ");
			int qoh = Integer.parseInt(kb.nextLine());

			Product toBeCreated = new Product(name, price, qoh);
			int id = service.createNewProduct(toBeCreated);
			System.out.println("Created a Product with id: " + id);
		}
	}
}
