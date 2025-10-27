package product.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import product.page.entity.DetailsEntity;

public interface DetailsRepo extends JpaRepository<DetailsEntity,Long> {
}
