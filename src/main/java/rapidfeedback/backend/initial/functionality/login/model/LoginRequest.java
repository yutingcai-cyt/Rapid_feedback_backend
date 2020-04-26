package rapidfeedback.backend.initial.functionality.login.model;

import lombok.Data;

/**
 * @author cyt
 * @date 2020/4/14
 */
@Data
public class LoginRequest {

    private String username;

    private String password;
}
