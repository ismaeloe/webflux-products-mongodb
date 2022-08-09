package mx.com.ismaeloe.productservicerxmongo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
	
	private String id;
	private String description;
	private Double price;
	
}
