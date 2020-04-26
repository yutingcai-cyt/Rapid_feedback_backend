package rapidfeedback.backend.initial.functionality.login.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import rapidfeedback.backend.initial.model.Project;

import java.util.List;

/**
 * @author cyt
 * @date 2020/4/14
 */

@Data
@Builder
public class LoginResponse {

    private String token;

    private String firstName;

    private List<Project> list;


    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
