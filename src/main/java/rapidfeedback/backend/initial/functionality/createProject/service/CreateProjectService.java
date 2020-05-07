package rapidfeedback.backend.initial.functionality.createProject.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.functionality.createProject.dao.CreateProjectDao;

import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.model.CreateProject;
import rapidfeedback.backend.initial.model.Project;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wmn
 * @date 02/05/2020
 */

@Slf4j
@Service
public class CreateProjectService implements CreateProjServiceInterface{
    @Autowired
    CreateProjectDao createProjectDao;

    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;

    @Override
    public CompletableFuture<CreateProjResponse> createProject(Integer markerId, Project project) {
        return CompletableFuture.supplyAsync(() -> {
            createProjectDao.createProject(project);
            createProjectDao.linkMarkerAndProj(markerId, project.getId());
            return CreateProjResponse.builder()
                    .id(project.getId())
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
}

