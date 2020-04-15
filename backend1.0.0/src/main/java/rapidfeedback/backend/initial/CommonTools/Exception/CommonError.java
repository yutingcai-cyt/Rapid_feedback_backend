package rapidfeedback.backend.initial.CommonTools.Exception;


public enum CommonError implements ErrorInfoInterface {
    SUCCESS("200", "success!"),
    UNAUTHORIZED("401","unauthrized"),
    BAD_REQUEST("400", "bad request!"),
    NOT_FOUND("404","can't find resources"),
    INTERNAL_SERVER_ERROR("500","server internal error!"),
    SERVER_BUSY("503","server is busy! Please try later")


    ;
    private String resultCode;

    private String resultMsg;

    CommonError(String resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return this.resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
