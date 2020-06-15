package rapidfeedback.backend.initial.functionality.login.Service;

import rapidfeedback.backend.initial.functionality.login.model.LoginResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author cyt
 * @date 2020/4/14
 */
public interface LoginServiceInterface {

    /**
     * login function
     * @param username
     * @param password
     * @return loginResponse
     */
    CompletableFuture<LoginResponse> login(String username, String password);
}
