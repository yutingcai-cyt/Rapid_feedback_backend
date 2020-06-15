package rapidfeedback.backend.initial.functionality.email.model;

import lombok.Data;

/**
 * @author cyt
 * @date 2020/5/28
 */
@Data
public class Assessment {
    public Assessment(){};

    Integer proj_id;

    Integer marker_id;

    Integer student_id;

    Integer group_id;

    Integer criteria_id;

    String comment;

    Double score;

    String assessed_date;
}
