package shared.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Article
 */
@Data
@NoArgsConstructor
@ToString
@JsonPropertyOrder({ "id", "label", "action", "number" })
public class ArticleEvent {
    public static enum Action {
        ADD, SUB
    }

    private Long id;
    private String label;
    private Action action;
    private Integer number;

    public ArticleEvent(Long id, String label, Action action, Integer number) {
        this.id = id;
        this.label = label;
        this.action = action;
        this.number = number;
    }

}