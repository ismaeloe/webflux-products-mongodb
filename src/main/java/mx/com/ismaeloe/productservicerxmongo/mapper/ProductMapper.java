package mx.com.ismaeloe.productservicerxmongo.mapper;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import mx.com.ismaeloe.productservicerxmongo.dto.ProductDto;
import mx.com.ismaeloe.productservicerxmongo.entity.Product;

@Mapper
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );
	
	//@Mapping( source = "product" , target = "productDto")
	ProductDto toDto(Product product);

}
