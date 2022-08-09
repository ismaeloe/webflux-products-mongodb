package mx.com.ismaeloe.productservicerxmongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.ismaeloe.productservicerxmongo.dto.ProductDto;
import mx.com.ismaeloe.productservicerxmongo.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product-api")	//ok "product-api"
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping	//ok ("all")
	public Flux<ProductDto> all() {
		return this.service.getAll();
	}
	
	/*
	 * Error with
	 * @GetMapping("{id}") Error java.lang.IllegalArgumentException: The given id must not be null!
	 * public Mono<ResponseEntity<ProductDto>> getProductDtoById(String id) {
	 */ 
	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<ProductDto>> getProductDtoById(@PathVariable("id") String id) {
		
		System.err.println("ID = " + id);
		
		return this.service.getProductById(id)
							.map(ResponseEntity::ok)
							.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<ProductDto> insertProduct( @RequestBody Mono<ProductDto> productDto) {
		return this.service.insertProduct(productDto);
	}

	@PutMapping("{id}")
	public Mono<ResponseEntity<ProductDto>> updateProduct(
											@PathVariable String id ,
											@RequestBody Mono<ProductDto> productDto) {

		return this.service.updateProduct(id, productDto)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	public Mono<Void> deleteProduct(@PathVariable String id)
	{
		return this.service.deleteProduct(id);
	}

}
