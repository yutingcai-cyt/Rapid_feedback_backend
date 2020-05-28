package rapidfeedback.backend.initial.functionality.updateProject.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class AddCriteriaResponse {
    @Tolerate
    public AddCriteriaResponse(){};

    private Integer criteriaId;

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
