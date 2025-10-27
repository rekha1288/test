package product.page.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.page.entity.CustomerEntity;
import product.page.repository.CustomerRepo;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    // ✅ Save product
    public CustomerEntity saveProduct(CustomerEntity customerEntity) {
        return customerRepo.save(customerEntity);
    }

    // ✅ Get all products
    public List<CustomerEntity> getAllProducts() {
        return customerRepo.findAll();
    }

    // ✅ Get product by ID
    public CustomerEntity getProductById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    // ✅ Delete product
    public void deleteProduct(Long id) {
        customerRepo.deleteById(id);
    }
}
