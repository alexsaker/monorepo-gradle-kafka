package shared.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ArticleDto
 */
@Data
@NoArgsConstructor
@ToString
@JsonPropertyOrder({ "label", "type", "brand", "price", "internalStock" })
public class ArticleDto {
    private String label;
    private String brand;
    private String type;
    private String description;
    private Double price;
    private Integer internalStock;

    public ArticleDto(String label, String brand, String type, String description, Double price,
            Integer internalStock) {
        this.label = label;
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.price = price;
        this.internalStock = internalStock;
    }

}