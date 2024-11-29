package ls.andres.springboot.app.springboot_crud.repository;

import ls.andres.springboot.app.springboot_crud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
