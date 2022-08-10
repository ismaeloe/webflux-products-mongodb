package mx.com.ismaeloe.productservicerxmongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import mx.com.ismaeloe.productservicerxmongo.dto.ProductDto;
import mx.com.ismaeloe.productservicerxmongo.repository.ProductRepository;
import mx.com.ismaeloe.productservicerxmongo.util.EntityDtoUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public Flux<ProductDto> getAll() {
		//return this.repository.findAll().map( product -> new ProductDto(product.getId(), product.getDescription(), product.getPrice() ));
		//return this.repository.findAll().map( p -> EntityDtoUtil.toDto(p));
		return this.repository.findAll()
								.map( EntityDtoUtil::toDto );
	}
	
	public Flux<ProductDto> getProductByPriceBetween(int min, int max) {
		return this.repository.findByPriceBetween(min, max)		// Exclusive > 1 &&  < 10
								.map( EntityDtoUtil::toDto );
	}

	public Flux<ProductDto> getProductByPriceRange(int min, int max) {
		return this.repository.findByPriceBetween(Range.closed(min, max)) 	//		Inclusive >=1 && =< 10
								.map( EntityDtoUtil::toDto ); //Range.open(1, 10) 	Exclusive > 1 &&  < 10 
	}

	public Mono<ProductDto> getProductById(String id) {
		return this.repository.findById(id)
								.map( EntityDtoUtil::toDto );
	}

	public Mono<ProductDto> insertProduct( Mono<ProductDto> productDtoMono) {
		
		return productDtoMono.map( EntityDtoUtil::toEntity)
							.flatMap( this.repository::insert )
							.map( EntityDtoUtil::toDto );
	}

	public Mono<ProductDto> updateProduct(String id ,Mono<ProductDto> productDtoMono) {
		
		return this.repository.findById(id)
								.flatMap(product ->
									 	 productDtoMono.map(EntityDtoUtil::toEntity)
														.doOnNext( e -> e.setId(id)) )
								.flatMap( this.repository::save)
								.map(EntityDtoUtil::toDto);
	}

	public Mono<Void> deleteProduct(String id) {
		return this.repository.deleteById(id);
	}
}
