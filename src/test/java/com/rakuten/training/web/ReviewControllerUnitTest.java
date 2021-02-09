package com.rakuten.training.web;

import java.util.ArrayList;
import java.util.List;

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
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {})
class ReviewControllerUnitTest {

	@MockBean
	ReviewService mockReviewSvc;

	@MockBean
	ProductService mockProductSvc;

	@Autowired
	MockMvc mockMvc;

	@Test
	void testFindReviewByPid() throws Exception {

		int pid = 1;

		// not required
//		Review r1 = new Review("test1", "quite good", pid);
//		Review r2 = new Review("test2", "quite bad", pid);
//		List<Review> listReturnedByMock = new ArrayList<>();
//		listReturnedByMock.add(r1);
//		listReturnedByMock.add(r2);

		Product productDataReturnedByMock = new Product("test", 12345, 5);
		productDataReturnedByMock.setId(pid);

		Mockito.when(mockProductSvc.findById(pid)).thenReturn(productDataReturnedByMock);

		mockMvc.perform(MockMvcRequestBuilders.get("/products/{pid}/reviews", pid).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		// we should not use string comparison to compare if json is returned because
		// json might contain different formatting and string will interpret spaces
		// adn diferent json
		// unmarshall the json into a java object, and compare java objects using equals
		// comparision
		// but it requires to be that equals method overridden, it is a lot

	}

//	@Test
//	void testaddReviewToAProduct() throws Exception {
//		int pid = 1;
//		Product productDataReturnedByMock = new Product("test", 12345, 5);
//		productDataReturnedByMock.setId(pid);
//		Mockito.when(mockProductSvc.findById(pid)).thenReturn(productDataReturnedByMock);
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/products/{pid}/reviews", pid).accept(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//
//	}

}
