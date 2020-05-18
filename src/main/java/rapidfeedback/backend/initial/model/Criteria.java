package rapidfeedback.backend.initial.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Criteria {

    private Integer criteriaId;
    private Integer weight;
}
