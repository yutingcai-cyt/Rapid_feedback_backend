package rapidfeedback.backend.initial.functionality.updateProject.service;

import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.updateProject.model.getCriteriaListResponse;
import rapidfeedback.backend.initial.model.Criteria;
import rapidfeedback.backend.initial.model.Project;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UpdateProjService {
    CompletableFuture<CreateProjResponse> updateProject(Project project, Integer projectId);

    CompletableFuture<Void> addMarker(Integer markerId, Integer projectId);

    void updateCriteria(Integer projectId, List<Criteria> criteriaList);

    CompletableFuture<getCriteriaListResponse> getCriteriaList(Integer projectId);
}
