package product.page.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import product.page.entity.ProductEntity;
import product.page.service.ProductService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ✅ Show admin dashboard (Add product form)
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "adminDashboard";
    }

    // ✅ Add product → Save in DATABASE
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductEntity product,
                             @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        productService.saveProduct(product, imageFile);
        return "redirect:/admin/productList"; // Redirect to updated list
    }

    // ✅ Display products FROM DATABASE
    @GetMapping("/productList")
    public String showProducts(Model model) {
        List<ProductEntity> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "productList";
    }

    @GetMapping("/productView")
    public String showAllProducts(Model model) {
        List<ProductEntity> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "product";
    }

    // ✅ Product details page (from DB)
    @GetMapping("/product/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        ProductEntity product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/page"; // fallback
        }
        model.addAttribute("product", product);
        return "productDetails"; // Thymeleaf template
    }




    @GetMapping("/showDetails")
    public String showProductDetailsTable(Model model) {
        List<ProductEntity> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "showDetails"; // Thymeleaf template name
    }


    // (Optional) Test endpoint for index.html
    @GetMapping("/temp")
    public String method(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "html/index";
    }
}
