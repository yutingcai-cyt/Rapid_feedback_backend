package rapidfeedback.backend.initial.functionality.loadProjectList.Service;

import rapidfeedback.backend.initial.functionality.loadProjectList.model.LoadProjectRespond;

import java.util.concurrent.CompletableFuture;

public interface LoadProjectServiceInterface {

    CompletableFuture<LoadProjectRespond> loadProject(Integer id);
}
