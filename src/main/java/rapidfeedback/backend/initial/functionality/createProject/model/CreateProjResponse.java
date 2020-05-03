package rapidfeedback.backend.initial.functionality.createProject.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjResponse {

    private Integer id;

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}

