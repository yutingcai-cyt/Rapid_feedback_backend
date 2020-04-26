package rapidfeedback.backend.initial.CommonTools.Exception;

import lombok.Data;

/**
 * @author cyt
 * @date 2020/4/14
 */
@Data
public class ResultBody {
    private String resultCode;

    private String message;

    private Object result;

    public ResultBody(ErrorInfoInterface errorInfoInterface){
        this.resultCode = errorInfoInterface.getResultCode();
        this.message = errorInfoInterface.getResultMsg();
    }

    public ResultBody(String code, String msg){
        this.resultCode = code;
        this.message = msg;
    }

    /**
     *
     * @param errorInfo
     * @return ResultBody
     */
    public static ResultBody error(ErrorInfoInterface errorInfo){
        return new ResultBody(errorInfo);
    }

    public static ResultBody error(String code, String msg){
        return new ResultBody(code,msg);
    }


}
