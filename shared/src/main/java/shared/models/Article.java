package shared.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Article
 */
@Data
@NoArgsConstructor
@ToString
@JsonPropertyOrder({ "id", "label", "type", "brand", "price", "internalStock" })
public class Article {
    private Long id;
    private String label;
    private String brand;
    private String type;
    private String description;
    private Double price;
    private Integer internalStock;

    public Article(Long id, String label, String brand, String type, String description, Double price,
            Integer internalStock) {
        this.id = id;
        this.label = label;
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.price = price;
        this.internalStock = internalStock;
    }
}