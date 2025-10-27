package product.page.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import product.page.entity.PageEntity;
import product.page.repository.PageRepo;

@Configuration
public class PageInitializer {

    @Bean
    CommandLineRunner initPageItems(PageRepo pageRepo) {
        return args -> {
            pageRepo.deleteAll();

            // ✅ Add Tops
            pageRepo.save(createProduct("Casual Top 1", "Stylish top for women", 299, "/images/top-1.jpg"));
            pageRepo.save(createProduct("Casual Top 2", "Stylish top for women", 269, "/images/top-2.webp"));
            pageRepo.save(createProduct("Casual Top 3", "Stylish top for women", 399, "/images/top-3.jpg"));
            pageRepo.save(createProduct("Casual Top 4", "Stylish top for women", 339, "/images/top-4.webp"));
            pageRepo.save(createProduct("Casual Top 5", "Stylish top for women", 599, "/images/top-5.webp"));
            pageRepo.save(createProduct("Casual Top 6", "Stylish top for women", 359, "/images/top-6.jpg"));
            pageRepo.save(createProduct("Casual Top 7", "Stylish top for women", 999, "/images/top-7.webp"));
            pageRepo.save(createProduct("Casual Top 8", "Stylish top for women", 1299, "/images/top-8.jpg"));
            pageRepo.save(createProduct("Casual Top 9", "Stylish top for women", 499, "/images/top-9.jpg"));
            pageRepo.save(createProduct("Casual Top 10", "Stylish top for women", 349, "/images/top-10.jpg"));

            // ✅ Shirts
            pageRepo.save(createProduct("Shirt 1", "Comfortable cotton shirt", 499, "/images/shirt-1.jpg"));
            pageRepo.save(createProduct("Shirt 2", "Comfortable cotton shirt", 599, "/images/shirt-2.jpg"));
            pageRepo.save(createProduct("Shirt 3", "Comfortable cotton shirt", 349, "/images/shirt-3.webp"));
            pageRepo.save(createProduct("Shirt 4", "Comfortable cotton shirt", 699, "/images/shirt-4.jpg"));
            pageRepo.save(createProduct("Shirt 5", "Comfortable cotton shirt", 549, "/images/shirt-5.webp"));
            pageRepo.save(createProduct("Shirt 6", "Comfortable cotton shirt", 369, "/images/shirt-6.jpg"));
            pageRepo.save(createProduct("Shirt 7", "Comfortable cotton shirt", 795, "/images/shirt-7.jpg"));
            pageRepo.save(createProduct("Shirt 8", "Comfortable cotton shirt", 869, "/images/shirt-8.jpg"));
            pageRepo.save(createProduct("Shirt 9", "Comfortable cotton shirt", 391, "/images/shirt-9.jpg"));
            pageRepo.save(createProduct("Shirt 10", "Comfortable cotton shirt", 496, "/images/shirt-11.jpg"));

            System.out.println("✅ Page items initialized successfully!");
        };
    }

    // ✅ Helper method to create a product entity cleanly
    private PageEntity createProduct(String name, String description, double price, String img) {
        PageEntity product = new PageEntity();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(img);
        product.setQuantity(1);
        product.setType("product");
        return product;
    }
}
