package rapidfeedback.backend.initial.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndividualResult {
    private Integer markerId;
    private String firstName;
    private String lastName;
    private String assessedDate;
    private String criteria;
    private Double score;
    private String comment;
}
