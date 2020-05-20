package rapidfeedback.backend.initial.functionality.student.model;

import lombok.Data;
import rapidfeedback.backend.initial.model.Student;

import java.util.List;

/**
 * @author cyt
 * @date 2020/5/20
 */
@Data
public class BatchAddStudentRequest {
    List<Student> studentList;

    Integer project_id;
}
