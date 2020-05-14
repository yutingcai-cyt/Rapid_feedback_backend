package rapidfeedback.backend.initial.functionality.deleteProject.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.functionality.createProject.dao.CreateProjectDao;

import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.deleteProject.dao.DeleteProjDao;
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
public class DeleteProjectService implements DeleteProjService {

    @Autowired
    DeleteProjDao deleteProjDao;

    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;

    @Override
    public void deleteProject(Integer projectId){
        deleteProjDao.deleteProject(projectId);
    }
}
