package rapidfeedback.backend.initial.functionality.assess.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class AddCommentResponse {

    private Integer commentId;

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
