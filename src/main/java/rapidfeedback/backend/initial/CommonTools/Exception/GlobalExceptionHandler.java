package rapidfeedback.backend.initial.CommonTools.Exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger loggeer = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * deal with the service exception of Rapid Feedback
     * @param e
     * @return
     */
    @ExceptionHandler(value = FBException.class)
    @ResponseBody
    public ResultBody FBExceptionHandler(FBException e){
        loggeer.error("there is a service error: {}", e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }


    public ResultBody exceptionHandler(Exception e){
        loggeer.error("Unknown exception: ", e);
        return ResultBody.error(CommonError.INTERNAL_SERVER_ERROR);
    }
}
