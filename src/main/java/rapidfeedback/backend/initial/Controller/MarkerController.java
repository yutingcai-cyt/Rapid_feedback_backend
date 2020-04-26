package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.login.Dao.LoginDao;
import rapidfeedback.backend.initial.functionality.login.Service.LoginService;
import rapidfeedback.backend.initial.functionality.login.model.LoginRequest;
import rapidfeedback.backend.initial.functionality.login.model.LoginResponse;
import rapidfeedback.backend.initial.model.Marker;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author cyt
 * @date 2020/4/14
 */

@Slf4j
@RestController
@RequestMapping("/v1/Marker")
public class MarkerController {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private LoginService loginService;

    @Resource(name = "controllerThreadPool")
    private ThreadPoolExecutor executor;

    @GetMapping("/all")
    public List<Marker> getAll(){

        return loginDao.findAll();
    }

    @PostMapping("/login")
    public CompletableFuture<ResponseEntity<LoginResponse>> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest){
        String token = Token.tokenGenerate();
        request.getSession().setAttribute("token",token);
        return loginService.login(loginRequest.getUsername(), loginRequest.getPassword())
                .thenApplyAsync(loginResponse -> {
                    loginResponse.setToken(token);
                    log.info("user {} login with token {}", loginRequest.getUsername(),token);
                    return ResponseEntity.ok(loginResponse);
                },executor);
    }

}
