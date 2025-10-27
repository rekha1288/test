package product.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import product.page.entity.PageEntity;

public interface PageRepo extends JpaRepository<PageEntity,Long> {
}
