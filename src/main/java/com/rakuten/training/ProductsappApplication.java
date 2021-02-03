package com.rakuten.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.training.ui.ProductConsoleUI;

@SpringBootApplication
public class ProductsappApplication {
	// shows SPRING for dependency injection DI

	public static void main(String[] args) {
		ApplicationContext springContainer = SpringApplication.run(ProductsappApplication.class, args);

		// if we comment line 11,27,28, and use only 22 and 23 lines
		// THIS IS NOT A BEAN, as it is created by us
		// BEAN- is an object or a class instance that is CREATED and MANAGED BY THE
		// CONTAINER
		// but we are creating it
		// so it wont use those dependencies, as those dependencies are provided to
		// Spring
//		ProductConsoleUI ui = new ProductConsoleUI();
//		ui.createProductWithUI();

		// this was not object created by spring container
		// so->
		ProductConsoleUI ui = springContainer.getBean(ProductConsoleUI.class);
		// short answer: argument type of get bean is arg type of class, syntax for
		// representing a class
		// long answer: in java, there is a Class called Class, every class or interface
		// loaded into memory, is internally represented by jvm as an object of type
		// class, this is called Reflection API
		// literal way of creating an obj of Class is, .class(dotclass)
		ui.createProductWithUI();
	}

}
