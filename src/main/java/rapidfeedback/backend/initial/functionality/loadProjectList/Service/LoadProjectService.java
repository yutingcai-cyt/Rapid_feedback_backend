package rapidfeedback.backend.initial.functionality.loadProjectList.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.functionality.loadProjectList.Dao.LoadProjectDao;
import rapidfeedback.backend.initial.functionality.loadProjectList.model.LoadProjectRespond;
import rapidfeedback.backend.initial.model.Project;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.List;

/**
 * @author wmn
 * @date 2020/4/30
 */

@Slf4j
@Service
public class LoadProjectService implements LoadProjectServiceInterface {

    @Autowired
    LoadProjectDao loadProjectDao;

    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;



    @Override
    public CompletableFuture<LoadProjectRespond> loadProject(Integer id) {

        CompletableFuture<List<Project>> future = CompletableFuture.supplyAsync(() -> loadProjectDao.findProjectsById(id));
        return future.thenApplyAsync(projects -> {
            log.info("Projects load");
            return LoadProjectRespond.builder()
                    .projList(projects).build();
        }, executor);
    }
}
