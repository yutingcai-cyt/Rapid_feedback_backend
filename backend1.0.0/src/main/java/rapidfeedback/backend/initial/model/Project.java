package rapidfeedback.backend.initial.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class Project {
    private Integer id;
    private String name;
    private String subjectName;
    private String subjectCode;
    private Integer durationSec;
    private Integer warningSec;
    private String description;
    private Integer idPrincipal;
}
