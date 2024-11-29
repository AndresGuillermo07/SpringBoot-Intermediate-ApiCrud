package ls.andres.springboot.app.springboot_crud.service;

import ls.andres.springboot.app.springboot_crud.entity.Product;
import ls.andres.springboot.app.springboot_crud.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product productDB = optionalProduct.orElseThrow();

            productDB.setProductName(product.getProductName());
            productDB.setProductDescription(product.getProductDescription());
            productDB.setProductPrice(product.getProductPrice());

            return Optional.of(productRepository.save(productDB));
        }

        return optionalProduct;
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(productRepository::delete);
        return optionalProduct;
    }


}
