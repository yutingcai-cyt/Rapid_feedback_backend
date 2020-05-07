package rapidfeedback.backend.initial.functionality.updateProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void updateProject(Project project, Integer projectId){
        updateProjectDao.updateProject(project, projectId);
    }
}
