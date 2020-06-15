package rapidfeedback.backend.initial.functionality.createProject.service;

import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.model.Project;

import java.util.concurrent.CompletableFuture;

public interface CreateProjServiceInterface {
    CompletableFuture<CreateProjResponse> createProject(Integer markerId, Project project);
}
