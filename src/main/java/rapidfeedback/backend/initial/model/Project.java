package rapidfeedback.backend.initial.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;


@Data
public class Project implements Serializable {
    public Project(){}
    private Integer id;
    private String name;
    private String subjectName;
    private String subjectCode;
    private Integer durationSec;
    private Integer warningSec;
    private String description;
    private Integer idPrincipal;
}
