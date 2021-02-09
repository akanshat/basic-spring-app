package com.rakuten.training.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {})
class ProductControllerUnitTest {
	// this test will be run by junit
	// junit doesnt understand spring, so it will have to use an extension provided
	// by spring
	// involves web MVC

	@MockBean
	ProductService mockService;

	// all autowired dependencies to be declared here with annotation mockbean
	@MockBean
	ReviewService mockReviewSvc;

	// mockmvc 's purpose is to simulate requests ina test setup
	@Autowired
	MockMvc mockMvc;
	// spring should give me this object, therefore autowired

	@Test
	void testProductById() throws Exception {
		/// AAA
		// Arrange
		int id = 1;
		Product dataReturnedByMock = new Product("test", 12345, 5);
		dataReturnedByMock.setId(id);

		// training the mock
		Mockito.when(mockService.findById(id)).thenReturn(dataReturnedByMock);

		// interesting
		// ACT -> perform

		// ASSERT -> expect

		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", id).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
