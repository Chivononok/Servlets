package enterprise.service.impl;

import enterprise.dto.ProductDTO;
import enterprise.entity.Product;
import enterprise.mapper.ProductMapper;
import enterprise.repository.PostgreConnect;
import enterprise.repository.ProductRepository;
import enterprise.service.ProductService;

public class ProductServiceImpl implements ProductService {
    private PostgreConnect postgreConnect;

    public ProductServiceImpl(PostgreConnect postgreConnect){
        this.postgreConnect = postgreConnect;
    }

    public void addProduct(ProductDTO productDTO){
        ProductMapper productMapper = new ProductMapper();
        Product product = productMapper.toEntity(productDTO);
        ProductRepository productRepository = new ProductRepository(postgreConnect);
        productRepository.add(product);
    }
}
