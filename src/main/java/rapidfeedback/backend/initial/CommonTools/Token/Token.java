package rapidfeedback.backend.initial.CommonTools.Token;

import rapidfeedback.backend.initial.CommonTools.Exception.CommonError;
import rapidfeedback.backend.initial.CommonTools.Exception.FBException;


import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

/**
 * @author cyt
 * @date 2020/4/15
 */
public class Token {
    /**
     * generate token for login
     * @return String
     */
    public static String tokenGenerate() {

        String token = UUID.randomUUID().toString();
        return token;
    }


    /**
     * check the identity of user with token
     * @param request
     * @param token
     * @return boolean
     */
    public static boolean tokenCheck(HttpServletRequest request, String token){
        String savedToken = (String) request.getSession().getAttribute("token");

        if(savedToken == null){
            throw new FBException(CommonError.UNAUTHORIZED.getResultCode(), "token missing, please login again!");
        }else if(!savedToken.equals(token)){
            throw new FBException(CommonError.UNAUTHORIZED.getResultCode(), "wrong token, please login again!");
        }else{
            return true;
        }
    }
}
