package rapidfeedback.backend.initial.CommonTools.JsonTool;

import com.alibaba.fastjson.JSONObject;

/**
 * @author cyt
 * @date 2020/5/7
 */
public class JsonTransfer {



    public static <T> T transfer(Object origin, T copy){
        return (T)JSONObject.parseObject(JSONObject.toJSONString(origin),copy.getClass());
    }
}
