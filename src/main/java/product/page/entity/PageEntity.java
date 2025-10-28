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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 5000)
    private String description;

    private double price;
    private String imageUrl;
    private int quantity;

    private String type;
    private Long itemId;
    private String itemImg;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String postal;
    private String paymentMethod;

    private double total;
}
