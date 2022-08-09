package mx.com.ismaeloe.productservicerxmongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import org.springframework.stereotype.Repository;

import mx.com.ismaeloe.productservicerxmongo.entity.Product;


//public interface ProductRepository extends ReactiveCrudRepository<Account, String> {	
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
