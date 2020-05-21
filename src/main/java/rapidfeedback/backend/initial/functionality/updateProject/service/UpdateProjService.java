package rapidfeedback.backend.initial.functionality.updateProject.service;

import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.updateProject.model.GetMarkerResponse;
import rapidfeedback.backend.initial.model.Project;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UpdateProjService {
    CompletableFuture<CreateProjResponse> updateProject(Project project, Integer projectId);

    void addMarker(List<Integer> markerIdList, Integer projectId); //Todo: transfer to asyn

    CompletableFuture<GetMarkerResponse> getMarker(Integer projectId);

}
