package enterprise.mapper;

import enterprise.dto.ProductDTO;
import enterprise.entity.Product;

public class ProductMapper {
    public Product toEntity(ProductDTO productDTO){
         Product product = new Product(productDTO.getProductName(), productDTO.getProductPrice(), productDTO.getId());
        return product;
    }
}
