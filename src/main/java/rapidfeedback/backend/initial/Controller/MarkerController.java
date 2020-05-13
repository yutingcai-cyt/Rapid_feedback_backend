package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.createProject.service.CreateProjectService;
import rapidfeedback.backend.initial.functionality.loadProjectList.model.LoadProjectRespond;
import rapidfeedback.backend.initial.functionality.login.Dao.LoginDao;
import rapidfeedback.backend.initial.functionality.login.Service.LoginService;
import rapidfeedback.backend.initial.functionality.login.model.LoginRequest;
import rapidfeedback.backend.initial.functionality.login.model.LoginResponse;
import rapidfeedback.backend.initial.functionality.register.Service.RegisterService;
import rapidfeedback.backend.initial.functionality.loadProjectList.Service.LoadProjectService;
import rapidfeedback.backend.initial.model.Marker;
import rapidfeedback.backend.initial.model.Project;

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
@RequestMapping("/v1/markers")
public class MarkerController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoadProjectService loadProjectService;

    @Autowired
    private CreateProjectService createProjectService;

    @Resource(name = "controllerThreadPool")
    private ThreadPoolExecutor executor;

    @GetMapping("/all")
    public List<Marker> getAll(){

        return loginDao.findAll();
    }


    @PostMapping("/register")
    public CompletableFuture<ResponseEntity<LoginResponse>> register(HttpServletRequest request, @RequestBody Marker marker){
        String token = Token.tokenGenerate();
        request.getSession().setAttribute("token",token);


        return registerService.register(marker)
                .thenApplyAsync(loginResponse -> {
                    loginResponse.setToken(token);
                    log.info("user {} register with token {}", marker.getId(),token);
                    return ResponseEntity
                            .ok(loginResponse);
                },executor);
    }

    @PostMapping("/login")
    public CompletableFuture<ResponseEntity<LoginResponse>> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest){
        String token = Token.tokenGenerate();
        request.getSession().setAttribute("token",token);

        log.info("sessionid : {}", request.getSession().getId());
        return loginService.login(loginRequest.getUsername(), loginRequest.getPassword())
                .thenApplyAsync(loginResponse -> {
                    loginResponse.setToken(token);
                    log.info("user {} login with token {}", loginRequest.getUsername(),token);
                    return ResponseEntity.ok(loginResponse);
                },executor);
    }

    @GetMapping("/project")
    public CompletableFuture<ResponseEntity<LoadProjectRespond>> loadProjectList(HttpServletRequest request, @RequestParam Integer markerId){
        String token = request.getHeader("Authorization");
        return loadProjectService.loadProject(markerId)
                .thenApplyAsync(loadProjectRespond -> {
                    Token.tokenCheck(request, token);
                    log.info("user {}'s projects list", markerId);
                    return ResponseEntity.ok(loadProjectRespond);
                },executor);
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<CreateProjResponse>> createProject(HttpServletRequest request, @RequestBody Project project){
        String token = request.getHeader("Authorization");
        return createProjectService.createProject(project)
                .thenApplyAsync(createProjResponse -> {
                    Token.tokenCheck(request, token);
                    log.info("project {} created", project.getId());
                    return ResponseEntity.ok(createProjResponse);
                },executor);
    }
}
