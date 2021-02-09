package com.rakuten.training.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.rakuten.training.dal.ProductDAOInMemImpl;
import com.rakuten.training.domain.Product;

class ProductServiceImplTest {

	@Test
	void createNewProduct_Must_Return_Id_When_Value_Product_Value_GTEQ_MinValue() {
		// AAA
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 10000, 2);

		ProductDAOInMemImpl testDouble = new ProductDAOInMemImpl();
		objUnderTest.setDao(testDouble);

		// Act
		int id = objUnderTest.createNewProduct(argToMethod);
		// Assert
		assertTrue(id > 0);
	}

}
