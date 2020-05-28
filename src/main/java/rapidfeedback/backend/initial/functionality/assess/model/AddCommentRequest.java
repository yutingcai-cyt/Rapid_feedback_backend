package rapidfeedback.backend.initial.functionality.assess.model;

import lombok.Data;

@Data
public class AddCommentRequest {
    private Integer markerId;
    private String text;
    private String polarity;
}
