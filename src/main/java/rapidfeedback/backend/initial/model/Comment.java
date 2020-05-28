package rapidfeedback.backend.initial.model;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class Comment {

    private Integer id;
    private Integer markerId;
    private String text;
    private String polarity;

    @Tolerate
    public Comment(){}
}
