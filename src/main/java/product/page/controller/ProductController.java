package product.page.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import product.page.entity.PageEntity;
import product.page.entity.ProductEntity;
import product.page.service.ProductService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    //  Show admin dashboard (Add product form)
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "adminDashboard";
    }

    //  Add product → Save in DATABASE
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductEntity product, MultipartFile imageFile) throws IOException {
        productService.saveProduct(product,imageFile);
        return "redirect:/admin/productList"; // Redirect to updated list
    }

    //  Display products FROM DATABASE
    @GetMapping("/productList")
    public String showProducts(Model model) {
        List<ProductEntity> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "productList";
    }



    //  Checkout Page
    @GetMapping("/cart/checkOut")
    public String checkoutPage(Model model) {
        List<ProductEntity> cartItems = productService.getAllCartItems();
        double total = productService.calculateTotal();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("cartCount", cartItems.size());
        return "checkOut";
    }

    //  Product details page (from DB)
    @GetMapping("/product/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        ProductEntity product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/page"; // fallback
        }
        model.addAttribute("product", product);
        return "productDetails"; // Thymeleaf template
    }
    //  Cart Page
    @GetMapping("/cart")
    public String viewCartPage(Model model) {
        List<ProductEntity> cartItems = productService.getAllCartItems();
        double total = productService.calculateTotal();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("cartCount", cartItems.size());
        return "cart";
    }

    // Product listing page
    @GetMapping("/products")
    public String showAllProducts(Model model) {
        List<ProductEntity> products = productService.getAllProducts();
        int cartCount = productService.getAllCartItems().size();

        model.addAttribute("products", products);
        model.addAttribute("cartCount", cartCount);

        return "product"; // make sure you have product.html under /templates
    }

    @GetMapping("/showDetails")
    public String showProductDetailsTable(Model model) {
        List<ProductEntity> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "productDetails"; // Thymeleaf template name
    }




    // (Optional) Test endpoint for index.html
    @GetMapping("/temp")
    public String method(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "html/index";
    }

    @GetMapping("/details")
    public String details(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "details";
    }

    @PostMapping("/viewDetails")
    public String showDetails(@ModelAttribute ProductEntity product,
                              @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        productService.saveProduct(product,imageFile);
        return "details";
    }

    @PostMapping("/productView")
    public String saveProduct(@ModelAttribute ProductEntity product,
                              @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        productService.saveProduct(product,imageFile);
        return "redirect:/admin/productList";
    }
}
