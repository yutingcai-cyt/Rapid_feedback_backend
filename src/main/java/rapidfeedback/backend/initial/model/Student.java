package rapidfeedback.backend.initial.model;

import lombok.Builder;
import lombok.Data;


@Data
public class Student {
    public Student(){};
    private Integer id;
    private Integer uni_student_number;
    private String first_name;
    private String last_name;
    private String uni_email;
}
