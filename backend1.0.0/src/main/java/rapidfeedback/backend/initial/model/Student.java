package rapidfeedback.backend.initial.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Student {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer studentNumber;
    private String email;
}
