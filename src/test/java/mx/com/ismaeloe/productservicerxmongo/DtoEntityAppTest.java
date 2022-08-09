package mx.com.ismaeloe.productservicerxmongo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.UUID;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.BeanUtils;

import mx.com.ismaeloe.productservicerxmongo.dto.ProductDto;
import mx.com.ismaeloe.productservicerxmongo.entity.Product;
import mx.com.ismaeloe.productservicerxmongo.mapper.ProductMapper;

//@TestClassOrder(ClassOrderer.OrderAnnotation.class )
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DtoEntityAppTest {
	
	@Test
	@Order(1)
	public void modelMappetTest() {
		//fail("Falta Implementacion..");
		//assertTrue(true, "ERROR INESPERADO");
		
		ModelMapper modelMapper = new ModelMapper();
		
		TypeMap<Product, ProductDto> typeMap = modelMapper.createTypeMap(Product.class, ProductDto.class);
		
		//typeMap.addMappings( mapper -> { mapper.map(Product::getId ,ProductDto::getId ) }
		
		final Product entity = this.getProduct();

		Runnable runnable = () -> modelMapper.map(entity, ProductDto.class);

		this.performanceTest("modelMappetTest", runnable);
	}

	@Test
	@Order(1)
	public void beanUtilsTest() {
		
		final Product entity = this.getProduct();
		
		Runnable runnable = () -> {
			
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties( dto ,entity );
		};
		
		this.performanceTest("beanUtilsTest", runnable);
	}


	@Test
	@Order(3)
	public void mapStructTest() {
		
		final Product entity = this.getProduct();
		
		Runnable runnable = () -> ProductMapper.INSTANCE.toDto(entity);
		
		this.performanceTest("mapStructTest", runnable);
	}

	@Test
	@Order(4)
	public void manualTest() {

		final Product entity = this.getProduct();
		
		Runnable runnable = () -> new ProductDto( entity.getId() ,entity.getDescription() ,entity.getPrice() );
		
		this.performanceTest("manualTest", runnable);
	}

	
	private Product getProduct() {
		Product product = new Product( UUID.randomUUID().toString() ,"Primer Producto" , Double.valueOf(123.45) );
		return  product;			
	}
	
	private void performanceTest(final String type , final Runnable runnable) {
		System.out.println("----------------------------");
		//3 iterations
		for (int j = 0; j < 3; j++)
		{
			long time1 = System.currentTimeMillis();

			//iterate 1000
			for (int i = 0 ; i < 100_000; i++) {
				runnable.run();
			}
			long time2 = System.currentTimeMillis();
			System.out.println(type + " :: " + (time2 - time1) + " ms");
		}
	}
	
}
