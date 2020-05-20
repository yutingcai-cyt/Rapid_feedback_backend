package rapidfeedback.backend.initial.functionality.createProject.model;


import lombok.Data;

@Data
public class CreateProjRequest {

    private Integer markerId;

    private String subject_code;
    private String subject_name;
    private String proj_name;
    private Integer duration;
    //private due_date;
    private Integer is_group;
    private String proj_description;
}
