package com.springboot.web.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.Model.Product;

@RestController
public class ProductServiceController {
	private static  Map<String, Product> productRepo =new HashMap<>();
	 static {
	      Product pasta = new Product();
	      pasta.setId("1");
	      pasta.setName("Pasta");
	      productRepo.put(pasta.getId(), pasta);
	      
	      Product almond = new Product();
	      almond.setId("2");
	      almond.setName("Coffee");
	      productRepo.put(almond.getId(), almond);
	   }
		/*
		 * @RequestMapping(value = "/products") public String getProductName() { return
		 * "pasta"; }
		 */

	 //GetMApping GET method REST Endpoint.
	   @RequestMapping(value = "/products")
	   public ResponseEntity<Object> getProduct() {
	      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	   }
	   //Post Mapping POSTAPI--Here, the request URI is /products,
	   //and it will return the String after storing the product into HashMap repository.
	   @RequestMapping(value = "/products", method = RequestMethod.POST)
	   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
	      productRepo.put(product.getId(), product);
	      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	   }
	   // PUTAPI--this request URI is /products/{id} --id need to be updated
	   @RequestMapping(value= "/products/{id}", method=RequestMethod.PUT)
	   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
		   productRepo.remove(id);
		   product.setId(id);
		    productRepo.put(id, product);
		    return new ResponseEntity<>("Product is updated Succesfully", HttpStatus.OK);
	   }
	   
	   //DELETEAPI
	   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
	      productRepo.remove(id);
	      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	   }

}
