package ls.andres.springboot.app.springboot_crud.service;

import ls.andres.springboot.app.springboot_crud.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id,Product product);

    Optional<Product> delete(Long id);

}
