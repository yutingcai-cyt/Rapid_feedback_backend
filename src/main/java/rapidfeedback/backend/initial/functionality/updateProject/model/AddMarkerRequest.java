package rapidfeedback.backend.initial.functionality.updateProject.model;

import lombok.Data;

import java.util.List;

@Data
public class AddMarkerRequest {
    private List<Integer> markerIdList;

    private Integer projectId;
}
