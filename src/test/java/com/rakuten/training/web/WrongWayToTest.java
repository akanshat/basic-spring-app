package com.rakuten.training.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rakuten.training.ProductController;
import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

class WrongWayToTest {

	@Test
	void testGetProductById() {
		// spring controller is calling the controllers based on the URI mappings
		// so we have to make an HTTP get request to these, from client point of view
		// conversion into json, mappings,  this should be covered in this test
		
		/// AAA
		// Arrange
		ProductController objUnderTest = new ProductController();
		ProductService mockService = Mockito.mock(ProductService.class);
		int id = 1;
		Product returnedByMock = new Product("Test", 8765, 12);
		returnedByMock.setId(id);

		// training the mock
		Mockito.when(mockService.findById(id)).thenReturn(returnedByMock);
		objUnderTest.service = mockService;

		// ACT
		ResponseEntity<Product> response = objUnderTest.getProductById(id);

		// Assert
		assertTrue(response.getBody().getId() == id);
		assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

}
