package rapidfeedback.backend.initial.functionality.deleteProject.service;

import java.util.concurrent.CompletableFuture;

public interface DeleteProjService {
    CompletableFuture<Void> deleteProject(Integer projectId);
}
