package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rapidfeedback.backend.initial.CommonTools.JsonTool.JsonTransfer;
import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjRequest;
import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.createProject.service.CreateProjectService;
import rapidfeedback.backend.initial.functionality.deleteProject.service.DeleteProjService;
import rapidfeedback.backend.initial.functionality.loadProjectList.Dao.LoadProjectDao;
import rapidfeedback.backend.initial.functionality.loadProjectList.model.LoadProjectRespond;
import rapidfeedback.backend.initial.functionality.loadProjectList.Service.LoadProjectService;
import rapidfeedback.backend.initial.functionality.updateProject.service.UpdateProjService;
import rapidfeedback.backend.initial.model.CreateProject;
import rapidfeedback.backend.initial.model.Project;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import javax.xml.ws.Response;
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

    @Autowired
    private UpdateProjService updateProjService;

    @Autowired
    private DeleteProjService deleteProjService;

    @Resource(name = "controllerThreadPool")
    private ThreadPoolExecutor executor;

    @GetMapping("/all")
    public List<Project> getAll(){

        return loadProjectDao.findAll();
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<LoadProjectRespond>> loadProjectList(HttpServletRequest request, @PathVariable("id") Integer id){
        log.info("sessionId : {}", request.getSession().getId());
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return loadProjectService.loadProject(id)
                .thenApplyAsync(loadProjectRespond -> {
                    log.info("user {}'s projects list", id);
                    return ResponseEntity.ok(loadProjectRespond);
                },executor);
    }

    @PostMapping("")
    public CompletableFuture<ResponseEntity<CreateProjResponse>> createProject(HttpServletRequest request, @RequestBody CreateProjRequest createProjRequest) {
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        Project project = JsonTransfer.transfer(createProjRequest, new Project());
        return createProjectService.createProject(createProjRequest.getMarkerId(), project)
                .thenApplyAsync(createProjResponse -> {
                    log.info("create project {} by user {}", project.getId(), createProjRequest.getMarkerId());
                    return ResponseEntity.ok(createProjResponse);
                }, executor);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<CreateProjResponse>> updateProject(HttpServletRequest request, @RequestBody Project project, @PathVariable("id") Integer projectId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return updateProjService.updateProject(project, projectId)
                .thenApplyAsync(createProjResponse -> {
                    log.info("user {} update project", projectId);
                    return ResponseEntity.ok(createProjResponse);
                }, executor);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteProject(HttpServletRequest request, @PathVariable("id") Integer projectId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return deleteProjService.deleteProject(projectId).thenApplyAsync(aVoid ->{
            log.info("delete project {}", projectId );
            return ResponseEntity.ok(aVoid);
        },executor);
    }
}
