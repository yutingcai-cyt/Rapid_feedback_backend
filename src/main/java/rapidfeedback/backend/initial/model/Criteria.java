package rapidfeedback.backend.initial.model;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;


@Builder
@Data
public class Criteria {

    private Integer criteriaId;
    private Integer weight;

    @Tolerate
    public Criteria() {}
}
