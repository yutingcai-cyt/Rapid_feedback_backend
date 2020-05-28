package rapidfeedback.backend.initial.functionality.updateProject.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder

public class GetMarkerResponse {

    private List<Integer> markerIdList;

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
