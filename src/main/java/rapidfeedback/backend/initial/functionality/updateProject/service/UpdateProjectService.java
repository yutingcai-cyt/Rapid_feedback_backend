package rapidfeedback.backend.initial.functionality.updateProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.updateProject.dao.UpdateProjectDao;
import rapidfeedback.backend.initial.model.Project;

import javax.annotation.Resource;
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

}
