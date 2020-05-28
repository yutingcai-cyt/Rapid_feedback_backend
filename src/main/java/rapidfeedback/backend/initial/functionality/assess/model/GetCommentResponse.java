package rapidfeedback.backend.initial.functionality.assess.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import rapidfeedback.backend.initial.model.Comment;

import java.util.List;

@Data
@Builder
public class GetCommentResponse {
    private List<Comment> comments;

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
