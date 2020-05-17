package rapidfeedback.backend.initial.functionality.updateProject.service;

import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.model.Project;

import java.util.concurrent.CompletableFuture;

public interface UpdateProjService {
    CompletableFuture<CreateProjResponse> updateProject(Project project, Integer projectId);

    CompletableFuture<Void> addMarker(Integer markerId, Integer projectId);

    //CompletableFuture<Void> setCriteria(Integer markerId, Integer projectId);
}
