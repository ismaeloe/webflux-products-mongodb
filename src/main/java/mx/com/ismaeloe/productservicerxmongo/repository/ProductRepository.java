package mx.com.ismaeloe.productservicerxmongo.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import mx.com.ismaeloe.productservicerxmongo.entity.Product;
import reactor.core.publisher.Flux;


//public interface ProductRepository extends ReactiveCrudRepository<Account, String> {	
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
	
	Flux<Product> findByPriceBetween(int min, int max);

	Flux<Product> findByPriceBetween(Range<Integer> range);

}
