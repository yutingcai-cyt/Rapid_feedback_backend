package rapidfeedback.backend.initial.functionality.register.Service;

import rapidfeedback.backend.initial.functionality.login.model.LoginResponse;
import rapidfeedback.backend.initial.model.Marker;

import java.util.concurrent.CompletableFuture;

/**
 * @author cyt
 * @date 2020/4/26
 */

public interface RegisterService {
    CompletableFuture<LoginResponse> register(Marker marker);
}
