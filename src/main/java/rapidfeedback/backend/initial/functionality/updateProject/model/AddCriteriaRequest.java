package rapidfeedback.backend.initial.functionality.updateProject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCriteriaRequest {

    private Integer projectId;

    private String content;
    private Integer weight;
}
