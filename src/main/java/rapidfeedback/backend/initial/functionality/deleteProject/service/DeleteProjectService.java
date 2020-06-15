package rapidfeedback.backend.initial.functionality.deleteProject.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rapidfeedback.backend.initial.functionality.deleteProject.dao.DeleteProjDao;


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
    public CompletableFuture<Void> deleteProject(Integer projectId){
        return CompletableFuture.runAsync(() -> deleteProjDao.deleteProject(projectId),executor);
    }
}
