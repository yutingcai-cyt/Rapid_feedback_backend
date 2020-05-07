package rapidfeedback.backend.initial.functionality.updateProject.service;

import rapidfeedback.backend.initial.model.Project;

import java.util.concurrent.CompletableFuture;

public interface UpdateProjService {
    void updateProject(Project project, Integer projectId);
}
