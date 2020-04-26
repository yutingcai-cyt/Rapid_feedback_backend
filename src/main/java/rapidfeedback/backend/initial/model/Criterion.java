package rapidfeedback.backend.initial.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Criterion {
    private Integer id;
    private String name;
    private Integer idAuthor;
}
