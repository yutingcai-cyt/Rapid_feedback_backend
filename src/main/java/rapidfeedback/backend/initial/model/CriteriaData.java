package rapidfeedback.backend.initial.model;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class CriteriaData {
    @Tolerate
    public CriteriaData(){}

    private Integer id;
    private String content;
}
