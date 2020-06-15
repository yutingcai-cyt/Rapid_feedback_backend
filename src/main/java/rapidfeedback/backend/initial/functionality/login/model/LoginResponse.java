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

public class LoginResponse {
    public LoginResponse(){};
    private String token;

    private String last_name;

    private String first_name;

    private Integer id;

    private Integer is_coordinator;



    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
