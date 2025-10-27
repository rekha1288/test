package product.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import product.page.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity,Long> {
}
