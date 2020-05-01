package rapidfeedback.backend.initial.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.HashMap;


@Data
@Builder
public class Project implements Serializable {

    private Integer id;
    private String subject_code;
    private String subject_name;
    private String proj_name;
    private Integer duration_min;
    private Integer duration_sec;
    private Integer is_group;
    private String proj_description;

    @Tolerate
    Project() {}
}
