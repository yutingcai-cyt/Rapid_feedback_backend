package rapidfeedback.backend.initial.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Assess {

    private Integer criteriaId;
    private String comment;
    private Double score;

}
