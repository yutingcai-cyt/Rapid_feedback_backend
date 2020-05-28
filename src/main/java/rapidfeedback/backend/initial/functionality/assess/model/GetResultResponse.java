package rapidfeedback.backend.initial.functionality.assess.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import rapidfeedback.backend.initial.model.IndividualResult;

import java.util.List;
@Data
@Builder
public class GetResultResponse {

    private List<IndividualResult> individualResults;

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
