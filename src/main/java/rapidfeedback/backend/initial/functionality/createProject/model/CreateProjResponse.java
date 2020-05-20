package rapidfeedback.backend.initial.functionality.createProject.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjResponse {

    private Integer id;
    private String subject_code;
    private String subject_name;
    private String proj_name;
    private Integer duration;
    //private due_date;
    private Integer is_group;
    private String proj_description;



    public String toString(){
        return JSONObject.toJSONString(this);
    }
}

