package product.page.service;

import org.springframework.stereotype.Service;
import product.page.entity.DetailsEntity;
import product.page.repository.DetailsRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsService {
    private final DetailsRepo detailsRepo;


    public DetailsService(DetailsRepo detailsRepo) {
        this.detailsRepo = detailsRepo;
    }

    public List<DetailsEntity> getAllProducts() {
        return detailsRepo.findAll();
    }

    // Fetch product by ID
    public DetailsEntity getProductById(Long id) {
        Optional<DetailsEntity> product = detailsRepo.findById(id);
        return product.orElse(null); // return null if not found
    }

    // Save a product
    public DetailsEntity saveProduct(DetailsEntity productDetail) {
        return detailsRepo.save(productDetail);
    }
}
