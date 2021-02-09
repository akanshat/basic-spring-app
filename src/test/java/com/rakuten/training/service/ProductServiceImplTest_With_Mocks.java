package com.rakuten.training.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

class ProductServiceImplTest_With_Mocks {

	@Test
	void createNewProduct_Must_Return_Id_When_Value_Product_Value_GTEQ_MinValue() {
		// AAA
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 10000, 2);
		Product returnedByMethod = new Product("test", 10000, 2);
		returnedByMethod.setId(1);

		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.save(argToMethod)).thenReturn(returnedByMethod);
		// this double can be trained, mockDAO should know ehat ot return when save is
		// called
		// so mock dao will be trained that when save is called please return object I
		// am giving to you

		objUnderTest.setDao(mockDAO);
		// Act
		int id = objUnderTest.createNewProduct(argToMethod);
		// Assert
		assertTrue(id > 0);
	}

}
