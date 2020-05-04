package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.createProject.service.CreateProjectService;
import rapidfeedback.backend.initial.functionality.loadProjectList.Dao.LoadProjectDao;
import rapidfeedback.backend.initial.functionality.loadProjectList.model.LoadProjectRespond;
import rapidfeedback.backend.initial.functionality.loadProjectList.Service.LoadProjectService;
import rapidfeedback.backend.initial.model.Project;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wmn
 * @date 04/05/2020
 */

@Slf4j
@RestController
@RequestMapping("/v1/projects")
public class ProjectController {

    @Autowired
    private LoadProjectDao loadProjectDao;

    @Autowired
    private LoadProjectService loadProjectService;

    @Autowired
    private CreateProjectService createProjectService;

    @Resource(name = "controllerThreadPool")
    private ThreadPoolExecutor executor;

    @GetMapping("/all")
    public List<Project> getAll(){

        return loadProjectDao.findAll();
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<LoadProjectRespond>> loadProjectList(HttpServletRequest request, @PathVariable("id") Integer id){
        String token = request.getHeader("Authorization");
        return loadProjectService.loadProject(id)
                .thenApplyAsync(loadProjectRespond -> {
                    Token.tokenCheck(request, token);
                    log.info("user {}'s projects list", id);
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
