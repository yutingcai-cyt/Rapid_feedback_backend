package rapidfeedback.backend.initial.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Comment {
    private Integer id;
    private String text;
    private Integer idField;
    private String type;
}
