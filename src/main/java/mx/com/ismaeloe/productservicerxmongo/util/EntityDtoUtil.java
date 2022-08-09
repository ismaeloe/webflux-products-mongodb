package mx.com.ismaeloe.productservicerxmongo.util;

import org.springframework.beans.BeanUtils;

import mx.com.ismaeloe.productservicerxmongo.dto.ProductDto;
import mx.com.ismaeloe.productservicerxmongo.entity.Product;

public class EntityDtoUtil {

	/*
	 * slowest Mapper 	org.modelmapper.modelmapper
	 * slower  Spring 	BeanUtils 
	 * Fastert MapStruct org.mapstruct.mapstruct
	 * Fastest Manual  
	 */
	public static ProductDto toDto(Product product) {

		ProductDto dto = new ProductDto();

		//TODO ineficiently, search for better way
		BeanUtils.copyProperties(product, dto);
		return dto;
	}

	public static Product toEntity(ProductDto dto) {
		
		Product entity = new Product();
		
		BeanUtils.copyProperties( dto ,entity );
		return entity;
	}
	
}
