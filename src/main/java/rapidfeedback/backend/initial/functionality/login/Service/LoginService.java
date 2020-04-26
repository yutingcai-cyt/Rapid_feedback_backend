package rapidfeedback.backend.initial.functionality.login.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.CommonTools.Exception.CommonError;
import rapidfeedback.backend.initial.CommonTools.Exception.FBException;
import rapidfeedback.backend.initial.functionality.login.Dao.LoginDao;
import rapidfeedback.backend.initial.functionality.login.model.LoginResponse;
import rapidfeedback.backend.initial.model.Marker;
import rapidfeedback.backend.initial.model.Project;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author cyt
 * @date 2020/4/14
 */
@Slf4j
@Service
public class LoginService implements LoginServiceInterface {

    @Autowired
    LoginDao loginDao;

    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;

    @Override
    public CompletableFuture<LoginResponse> login(String username, String password) {
        CompletableFuture<Marker> future = CompletableFuture.supplyAsync(() -> loginDao.findByUsernameAndPwd(username, password));
        return future.thenApplyAsync(marker -> {
            if (marker == null) {
                throw new FBException(CommonError.NOT_FOUND.getResultCode(), "password is wrong or username does not exist!");
            }
            log.info("marker id is {}",marker.getId());
            List<Project> list = loginDao.findProjectsById(marker.getId());
            return LoginResponse.builder()
                    .list(list)
                    .firstName(marker.getFirstName())
                    .token(marker.getEmail())
                    .build();
        }, executor);
    }
}
