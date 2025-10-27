package product.page.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import product.page.entity.ProductEntity;
import product.page.repository.ProductRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public ProductEntity saveProduct(ProductEntity product, MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            // ✅ Store uploaded images in external folder
            String uploadDir = "uploads/images/";

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            // ✅ Create a unique filename
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);

            // ✅ Save file bytes
            Files.write(filePath, imageFile.getBytes());

            // ✅ Save relative path for browser
            product.setImageUrl("/uploads/images/" + fileName);
        }

        return productRepo.save(product);
    }
    public java.util.List<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }
    public ProductEntity getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }
}
