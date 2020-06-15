package rapidfeedback.backend.initial.CommonTools.Exception;


import lombok.Data;

@Data
public class FBException extends RuntimeException {

    protected String errorCode;

    protected String errorMsg;

    public FBException() {
        super();
    }

    public FBException(ErrorInfoInterface errorInfo) {
        super(errorInfo.getResultCode());
        this.errorCode = errorInfo.getResultCode();
        this.errorMsg = errorInfo.getResultMsg();
    }

    public FBException(ErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getResultCode(), cause);
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public FBException(String errorMsg){
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public FBException(String errorCode, String errorMsg){
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public FBException(String errorCode, String errorMsg, Throwable cause){
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace(){
        return this;
    }
}
