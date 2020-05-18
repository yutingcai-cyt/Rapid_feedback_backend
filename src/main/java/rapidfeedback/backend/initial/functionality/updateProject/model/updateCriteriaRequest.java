package rapidfeedback.backend.initial.functionality.updateProject.model;


import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import rapidfeedback.backend.initial.model.Criteria;

import java.util.List;

@Data
@Builder
public class updateCriteriaRequest {

    private List<Criteria> criteriaList;

    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
