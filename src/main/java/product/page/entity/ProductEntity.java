package product.page.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pro_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String shortDescp;

    @Column(length = 5000)
    private String description;

    private double price;
    private String imageUrl;
    private String material;
    private String size;
    private String features;

    private int quantity;
    private String type;
}
