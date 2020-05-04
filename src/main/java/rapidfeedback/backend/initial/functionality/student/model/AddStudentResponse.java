package rapidfeedback.backend.initial.functionality.student.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author cyt
 * @date 2020/5/4
 */
@Data
@Builder
public class AddStudentResponse {

    private Integer uni_student_number;

    private String first_name;

    private String last_name;

    private String uni_email;

    private Integer project_id;

    private Integer student_id;

}
