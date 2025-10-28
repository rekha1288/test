package product.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import product.page.entity.DetailsEntity;
import product.page.service.DetailsService;

import java.util.List;

@Controller
public class DetailsController {

    private final DetailsService detailsService;

    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    // Show all products in grid
    @GetMapping("/view")
    public String showAllProducts(Model model) {
        List<DetailsEntity> products = detailsService.getAllProducts();
        model.addAttribute("products", products);
        return "product"; // product grid page
    }

    // Show single product details
    @GetMapping("/product/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        DetailsEntity product = detailsService.getProductById(id);
        if (product == null) {
            return "redirect:/view"; // fallback if product not found
        }
        model.addAttribute("product", product);
        return "productDetails"; // Thymeleaf template
    }

}
