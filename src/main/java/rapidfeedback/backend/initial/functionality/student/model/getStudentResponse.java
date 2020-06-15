package rapidfeedback.backend.initial.functionality.student.model;

import lombok.Data;

/**
 * @author cyt
 * @date 2020/5/16
 */
@Data
public class getStudentResponse {
    public getStudentResponse(){};
    private Integer id;
    private Integer uni_student_number;
    private String first_name;
    private String last_name;
    private String uni_email;
    private Integer group_id;
    private Integer isAssessed;


}
