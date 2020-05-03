package rapidfeedback.backend.initial.functionality.loadProjectList.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import rapidfeedback.backend.initial.model.Project;

import java.util.List;

/**
 * @author wmn
 * @date 2020/4/30
 */

@Data
@Builder
public class LoadProjectRespond {

    private List<Project> projList;

    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
