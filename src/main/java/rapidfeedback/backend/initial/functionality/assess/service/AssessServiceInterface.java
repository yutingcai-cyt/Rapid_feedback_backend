package rapidfeedback.backend.initial.functionality.assess.service;

import rapidfeedback.backend.initial.functionality.assess.model.AddCommentResponse;
import rapidfeedback.backend.initial.functionality.assess.model.GetCommentResponse;
import rapidfeedback.backend.initial.functionality.assess.model.GetResultResponse;
import rapidfeedback.backend.initial.model.Assess;
import rapidfeedback.backend.initial.model.Comment;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AssessServiceInterface {
    CompletableFuture<GetResultResponse> getIndividualResult(Integer projectId, Integer studentId);

    CompletableFuture<AddCommentResponse> addComment(Comment comment);

    void assess(List<Assess> assessList, Integer projectId, Integer markerId, Integer studentId, Integer groupId, String assessedDate);

    void updateAssess(List<Assess> assessList, Integer projectId, Integer markerId, Integer studentId, Integer groupId, String assessedDate);

    CompletableFuture<GetCommentResponse> getComment(Integer markerId);
}
