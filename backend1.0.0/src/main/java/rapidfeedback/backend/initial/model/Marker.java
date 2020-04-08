package rapidfeedback.backend.initial.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class Marker {
    private Integer id;
    private String email;
    private String pwd;
    private String firstName;
    private String middleName;
    private String lastName;
}
