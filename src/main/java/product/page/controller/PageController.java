package product.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import product.page.entity.PageEntity;
import product.page.service.PageService;

import java.util.List;

@Controller
@RequestMapping
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    // 🏠 Home Page
    @GetMapping("/page")
    public String home(Model model) {
        model.addAttribute("pageEntity", new PageEntity());
        return "index";
    }

    // 🛍️ Product listing page
    @PostMapping("/cart/add")
    @ResponseBody
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam String productImg,
            @RequestParam int quantity) {

        try {
            pageService.addToCartDirect(productId, name, price, productImg, quantity);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    // 🧾 Cart Page
    @GetMapping("/cart")
    public String viewCartPage(Model model) {
        List<PageEntity> cartItems = pageService.getAllCartItems();
        double total = pageService.calculateTotal();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("cartCount", cartItems.size());
        return "cart";
    }

    // 🛍️ Product listing page
    @GetMapping("/products")
    public String showAllProducts(Model model) {
        List<PageEntity> products = pageService.getAllProducts();
        int cartCount = pageService.getAllCartItems().size();

        model.addAttribute("products", products);
        model.addAttribute("cartCount", cartCount);

        return "product"; // make sure you have product.html under /templates
    }


    // 🚚 Checkout Page
    @GetMapping("/cart/checkOut")
    public String checkoutPage(Model model) {
        List<PageEntity> cartItems = pageService.getAllCartItems();
        double total = pageService.calculateTotal();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("cartCount", cartItems.size());
        return "checkOut";
    }

    // 🧾 Step 1: After checkout → show Order Summary (orderSuccess.html)
    @PostMapping("/order/success")
    public String showOrderSuccess(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String postal,
            @RequestParam String paymentMethod,
            Model model) {

        List<PageEntity> cartItems = pageService.getAllCartItems();
        double total = pageService.calculateTotal();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        model.addAttribute("city", city);
        model.addAttribute("postal", postal);
        model.addAttribute("paymentMethod", paymentMethod);

        return "orderSuccess"; // orderSuccess.html
    }

    // ✅ Step 2: Confirm order → final success page (order.html)
    @PostMapping("/order/final")
    public String confirmOrder(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String postal,
            @RequestParam String paymentMethod,
            Model model) {

        double total = pageService.calculateTotal();

        PageEntity order = new PageEntity();
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setEmail(email);
        order.setAddress(address);
        order.setCity(city);
        order.setPostal(postal);
        order.setPaymentMethod(paymentMethod);
        order.setType("order");
        order.setTotal(total);

        PageEntity savedOrder = pageService.saveMethod(order);
        pageService.clearCart();

        model.addAttribute("order", savedOrder);
        return "order"; // order.html
    }


    // 🛒 Add product to cart by ID
    @GetMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable Long id) {
        pageService.addProductToCart(id, 1); // default quantity = 1
        return "redirect:/cart"; // ✅ redirect to cart.html
    }

    @PostMapping("/cart/add/{id}")
    public String addToCartPost(@PathVariable Long id) {
        // Add 1 quantity to cart
        pageService.addProductToCart(id, 1);

        return "redirect:/cart"; // redirect to cart page
    }

    // ✅ Display Final Order Success Page (order.html)
    @GetMapping("/order")
    public String orderPage(Model model) {
        List<PageEntity> allOrders = pageService.getAllOrders();
        PageEntity lastOrder = allOrders.isEmpty() ? null : allOrders.get(allOrders.size() - 1);

        model.addAttribute("order", lastOrder);
        model.addAttribute("message", "Your order was successful!");
        return "order"; // order.html
    }
}
