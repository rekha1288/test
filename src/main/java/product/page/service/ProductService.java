package product.page.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import product.page.entity.ProductEntity;
import product.page.repository.ProductRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public ProductEntity saveProduct(ProductEntity product,MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            // Store uploaded images in external folder
            String uploadDir = "uploads/images/";

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            // Create a unique filename
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);

            // Save file bytes
            Files.write(filePath, imageFile.getBytes());

            // Save relative path for browser
            product.setImageUrl("/uploads/images/" + fileName);
        }

        return productRepo.save(product);
    }

    //  Calculate total cart price
    public double calculateTotal() {
        return getAllCartItems()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    //  Clear cart after order
    public void clearCart() {
        List<ProductEntity> cartItems = getAllCartItems();
        productRepo.deleteAll(cartItems);
    }


    // Get all products
    public List<ProductEntity> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .filter(item -> "product".equalsIgnoreCase(item.getType()))
                .collect(Collectors.toList());
    }

    //  Get all cart items
    public List<ProductEntity> getAllCartItems() {
        return productRepo.findAll()
                .stream()
                .filter(item -> "cart".equalsIgnoreCase(item.getType()))
                .collect(Collectors.toList());
    }

    public ProductEntity getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }
}
