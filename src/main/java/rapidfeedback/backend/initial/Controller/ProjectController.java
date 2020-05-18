package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.createProject.service.CreateProjectService;
import rapidfeedback.backend.initial.functionality.deleteProject.service.DeleteProjectService;
import rapidfeedback.backend.initial.functionality.loadProjectList.Dao.LoadProjectDao;
import rapidfeedback.backend.initial.functionality.loadProjectList.model.LoadProjectRespond;
import rapidfeedback.backend.initial.functionality.loadProjectList.Service.LoadProjectService;
import rapidfeedback.backend.initial.functionality.updateProject.model.getCriteriaListResponse;
import rapidfeedback.backend.initial.functionality.updateProject.model.getMarkerResponse;
import rapidfeedback.backend.initial.functionality.updateProject.service.UpdateProjectService;
import rapidfeedback.backend.initial.model.Criteria;
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
    private UpdateProjectService updateProjectService;

    @Autowired
    private DeleteProjectService deleteProjectService;

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

    @PostMapping("/{id}")
    public CompletableFuture<ResponseEntity<CreateProjResponse>> createProject(HttpServletRequest request, @RequestBody Project project, @PathVariable("id") Integer markerId) {
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return createProjectService.createProject(markerId, project)
                .thenApplyAsync(createProjResponse -> {
                    log.info("user {} create project", markerId);
                    return ResponseEntity.ok(createProjResponse);
                }, executor);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<CreateProjResponse>> updateProject(HttpServletRequest request, @RequestBody Project project, @PathVariable("id") Integer projectId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return updateProjectService.updateProject(project, projectId)
                .thenApplyAsync(createProjResponse -> {
                    log.info("user {} update project", projectId);
                    return ResponseEntity.ok(createProjResponse);
                }, executor);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(HttpServletRequest request, @PathVariable("id") Integer projectId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        deleteProjectService.deleteProject(projectId);
    }

    @PostMapping("/{id}/addMarker")
    public CompletableFuture<ResponseEntity<Void>> addMarker(HttpServletRequest request, @PathVariable(name = "id") Integer projectId, @RequestBody Integer markerId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return updateProjectService.addMarker(markerId, projectId).thenApplyAsync(aVoid ->{
            log.info("add marker {} into project {}", markerId,projectId );
            return ResponseEntity.ok(aVoid);
        },executor);
    }

    @GetMapping("/{id}/getMarker")
    public CompletableFuture<ResponseEntity<getMarkerResponse>> getMarker(HttpServletRequest request, @PathVariable(name = "id") Integer projectId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return updateProjectService.getMarker(projectId)
                .thenApplyAsync(getMarkerResponse -> {
                    log.info("project {}'s marker list", projectId);
                    return ResponseEntity.ok(getMarkerResponse);
                },executor);
    }

    @PutMapping("/{id}/setCriteria")
    public void updateCriteria(HttpServletRequest request, @PathVariable(name = "id") Integer projectId, @RequestBody List<Criteria> criteriaList){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        updateProjectService.updateCriteria(projectId, criteriaList);
    }

    @GetMapping("/{id}/getCriteria")
    public CompletableFuture<ResponseEntity<getCriteriaListResponse>> getCriteriaList(HttpServletRequest request, @PathVariable(name = "id") Integer projectId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return updateProjectService.getCriteriaList(projectId)
                .thenApplyAsync(getCriteriaListResponse -> {
                    log.info("project {}'s criteria list", projectId);
                    return ResponseEntity.ok(getCriteriaListResponse);
                },executor);
    }

}
