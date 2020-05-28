package rapidfeedback.backend.initial.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Builder;
import lombok.Data;


@Data
public class Marker {
    public Marker(){};
    private Integer id;
    private Integer uni_id;
    private String uni_email;
    private String password;
    private String first_name;
    private String last_name;
    private Integer is_coordinator;



}
