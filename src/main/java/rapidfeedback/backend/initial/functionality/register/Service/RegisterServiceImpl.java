package rapidfeedback.backend.initial.functionality.register.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.CommonTools.JsonTool.JsonTransfer;
import rapidfeedback.backend.initial.functionality.login.model.LoginResponse;
import rapidfeedback.backend.initial.functionality.register.Dao.RegisterDao;
import rapidfeedback.backend.initial.model.Marker;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author cyt
 * @date 2020/4/26
 */
@Service
@Slf4j
public class RegisterServiceImpl implements  RegisterService{

    @Autowired
    RegisterDao registerDao;

    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;

    @Override
    public CompletableFuture<LoginResponse> register(Marker marker) {

        return CompletableFuture.supplyAsync(() -> {
            registerDao.addMarker(marker);
            return JsonTransfer.transfer(marker, new LoginResponse());

        },executor);
    }
}
