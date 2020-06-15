package rapidfeedback.backend.initial.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CriteriaContent {

    private Integer criteriaId;
    private String criteriaContent;
    private Integer weight;
}
