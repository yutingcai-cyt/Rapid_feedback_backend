package rapidfeedback.backend.initial.functionality.updateProject.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.updateProject.dao.UpdateProjectDao;
import rapidfeedback.backend.initial.functionality.updateProject.model.getCriteriaListResponse;
import rapidfeedback.backend.initial.model.Criteria;
import rapidfeedback.backend.initial.model.Project;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
public class UpdateProjectService implements UpdateProjService{

    @Autowired
    UpdateProjectDao updateProjectDao;

    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;

    @Override
    public CompletableFuture<CreateProjResponse> updateProject(Project project, Integer projectId){

        return CompletableFuture.supplyAsync(() -> {
            updateProjectDao.updateProject(project, projectId);
            return CreateProjResponse.builder()
                    .id(projectId)
                    .subject_code(project.getSubject_code())
                    .subject_name(project.getSubject_name())
                    .proj_name(project.getProj_name())
                    .duration_min(project.getDuration_min())
                    .duration_sec(project.getDuration_sec())
                    .is_group(project.getIs_group())
                    .proj_description(project.getProj_description())
                    .build();
        },executor);
    }

    @Override
    public CompletableFuture<Void> addMarker(Integer markerId, Integer projectId){
        return CompletableFuture.runAsync(() -> updateProjectDao.addMarker(markerId, projectId),executor);
    }

    @Override
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public void updateCriteria(Integer projectId, List<Criteria> criteriaList){
        for (int i = 0; i < criteriaList.size(); i++) {
            updateProjectDao.updateCriteria(projectId, criteriaList.get(i).getCriteriaId(), criteriaList.get(i).getWeight());
        }
        log.info("updated");
    }

    @Override
    public CompletableFuture<getCriteriaListResponse> getCriteriaList(Integer projectId){
        CompletableFuture<List<Criteria>> future = CompletableFuture.supplyAsync(() -> updateProjectDao.getCriteriaList(projectId));
        return future.thenApplyAsync(criteria -> {
            log.info("Criteria load");
            return getCriteriaListResponse.builder()
                    .criteriaList(criteria).build();
        }, executor);
    }
}
