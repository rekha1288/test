package product.page.service;

import org.springframework.stereotype.Service;
import product.page.entity.PageEntity;
import product.page.repository.PageRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageService {

    private final PageRepo pageRepo;

    public PageService(PageRepo pageRepo) {
        this.pageRepo = pageRepo;
    }

    public PageEntity getProductById(Long id) {
        return pageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


    // ✅ Auto-detect type and save product/order
    public PageEntity saveMethod(PageEntity pageEntity) {
        if (pageEntity.getType() == null) {
            pageEntity.setType("product");
        }
        return pageRepo.save(pageEntity);
    }

    // 🛍️ Get all products
    public List<PageEntity> getAllProducts() {
        return pageRepo.findAll()
                .stream()
                .filter(item -> "product".equalsIgnoreCase(item.getType()))
                .collect(Collectors.toList());
    }

    // 🛒 Get all cart items
    public List<PageEntity> getAllCartItems() {
        return pageRepo.findAll()
                .stream()
                .filter(item -> "cart".equalsIgnoreCase(item.getType()))
                .collect(Collectors.toList());
    }

    // 📦 Get all orders
    public List<PageEntity> getAllOrders() {
        return pageRepo.findAll()
                .stream()
                .filter(item -> "order".equalsIgnoreCase(item.getType()))
                .collect(Collectors.toList());
    }

    // 🧮 Calculate total cart price
    public double calculateTotal() {
        return getAllCartItems()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    // 🧹 Clear cart after order
    public void clearCart() {
        List<PageEntity> cartItems = getAllCartItems();
        pageRepo.deleteAll(cartItems);
    }

    // 🛒 Add product to cart by ID
    public void addProductToCart(Long productId, int quantity) {
        Optional<PageEntity> productOpt = pageRepo.findById(productId);

        if (productOpt.isPresent()) {
            PageEntity product = productOpt.get();

            PageEntity cartItem = new PageEntity();
            cartItem.setType("cart");
            cartItem.setItemId(product.getId());
            cartItem.setItemImg(product.getImageUrl());
            cartItem.setName(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(quantity);

            pageRepo.save(cartItem);
        }
    }

    // 🛒 Add to cart directly via AJAX (from controller)
    public void addToCartDirect(Long productId, String name, double price, String productImg, int quantity) {
        PageEntity cartItem = new PageEntity();
        cartItem.setType("cart");
        cartItem.setItemId(productId);
        cartItem.setItemImg(productImg);
        cartItem.setName(name);
        cartItem.setPrice(price);
        cartItem.setQuantity(quantity);

        pageRepo.save(cartItem);
    }
}
