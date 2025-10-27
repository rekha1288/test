package product.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import product.page.entity.CustomerEntity;
import product.page.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    // Show single product details
    @GetMapping("/details/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        CustomerEntity product = customerService.getProductById(id);
        model.addAttribute("product", product);
        return "productDetails";
    }


    // Save product from form
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute CustomerEntity product) {
        customerService.saveProduct(product);
        return "redirect:/product";
    }

}
