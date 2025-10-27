package product.page.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "page_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ safer for DB auto increment
    private Long id;

    // ---------- 🛍️ Product Fields ----------
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private int quantity;

    // ---------- 🛒 Cart/Product Tracking ----------
    private String type;      // "product", "cart", or "order"
    private Long itemId;      // Reference to original product (for cart)
    private String itemImg;   // Duplicate product image for cart items

    // ---------- 💳 Order Fields ----------
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String postal;
    private String paymentMethod;

    private double total;
}
