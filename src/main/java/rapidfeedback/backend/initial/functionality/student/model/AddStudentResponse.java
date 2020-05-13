package rapidfeedback.backend.initial.functionality.student.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author cyt
 * @date 2020/5/4
 */
@Data
public class AddStudentResponse {
    public AddStudentResponse(){};
    Integer uni_student_number;

    String first_name;

    String last_name;

    String uni_email;

    Integer project_id;

    Integer student_id;

}
