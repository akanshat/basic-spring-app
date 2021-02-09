package com.rakuten.training.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

class ProductServiceImplTest_With_Mocks {

	// Junit runs these test, it isnt aware of mockito or spring, just executes
	// methods with @Test annotations adn sees for "assertions"

	// spring container is not coming into picture here -> called out of container
	// tests

	// 1. creating a mock is quicker than creating an entire spring container,
	// 2. then create a graph of dependencies and inject them (expensive),
	// 3. may not be feasible
	// Unit test -> test of one unit in isolation

	// In a test setup what we we want to throw a database deadlock related error,
	// in mock we will have to create a mock for such an error
	// building on fire? so shall we set a building on fire? NO, therefore mock
	// drills are there
	@Test
	void createNewProduct_Must_Return_Id_When_Value_Product_Value_GTEQ_MinValue() {
		// AAA
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 10000, 2);
		Product returnedByMethod = new Product("test", 10000, 2);
		returnedByMethod.setId(1);

		// Mockito is a mock object library
		// Mocking an interface is cheaper than mocking an object
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.save(argToMethod)).thenReturn(returnedByMethod);
		// this double can be trained, mockDAO should know what to return when save is
		// called
		// so mock dao will be trained that when save is called please return object I
		// am giving to you

		objUnderTest.setDao(mockDAO);
		// Act
		int id = objUnderTest.createNewProduct(argToMethod);
		// Assert
		assertTrue(id > 0);
	}

	@Test
	void createNewProduct_Must_Return_IllegalArgException_When_Value_Passed_LT_MinValue() {
		/// AAA
		// Arrange

		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 9999, 1);
		Assertions.assertThatThrownBy(() -> {
			objUnderTest.createNewProduct(argToMethod);
		}).isInstanceOf(IllegalArgumentException.class);

		// Act

		// Assert
	}

	@Test
	void remove_Existing_Product_if_Product_Exists_and_Value_LT_100k() {
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		int id = 1;
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Product returnedByMock = new Product("Test", 10000, 2);
		returnedByMock.setId(id);

		Mockito.when(mockDAO.findById(id)).thenReturn(returnedByMock);
		objUnderTest.setDao(mockDAO);
		// Act
		objUnderTest.removeExisting(id);
		Mockito.verify(mockDAO).deleteById(id);
	}

}
