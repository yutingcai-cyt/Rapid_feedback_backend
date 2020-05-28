package rapidfeedback.backend.initial.functionality.assess.model;

import lombok.Data;
import rapidfeedback.backend.initial.model.Assess;

import java.util.List;

@Data
public class AssessRequest {
    private List<Assess> assessList;

    private Integer projectId;
    private Integer markerId;
    private Integer studentId;
    private Integer groupId;
    private String assessedDate;
}
