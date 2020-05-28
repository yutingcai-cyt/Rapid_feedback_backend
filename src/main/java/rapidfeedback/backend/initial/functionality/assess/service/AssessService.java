package rapidfeedback.backend.initial.functionality.assess.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.functionality.assess.dao.AssessDao;
import rapidfeedback.backend.initial.functionality.assess.model.AddCommentResponse;
import rapidfeedback.backend.initial.functionality.assess.model.GetCommentResponse;
import rapidfeedback.backend.initial.functionality.assess.model.GetResultResponse;
import rapidfeedback.backend.initial.model.Assess;
import rapidfeedback.backend.initial.model.Comment;
import rapidfeedback.backend.initial.model.IndividualResult;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.List;


@Slf4j
@Service
public class AssessService implements AssessServiceInterface {
    @Autowired
    AssessDao assessDao;

    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;

    @Override
    public CompletableFuture<GetResultResponse> getIndividualResult(Integer projectId, Integer studentId) {
        CompletableFuture<List<IndividualResult>> future = CompletableFuture.supplyAsync(() -> assessDao.getIndividualResult(projectId, studentId));
        return future.thenApplyAsync(results -> {
            log.info("Individual result loaded.");
            return GetResultResponse.builder()
                    .individualResults(results).build();
        }, executor);
    }

    @Override
    public CompletableFuture<AddCommentResponse> addComment(Comment comment) {
        return CompletableFuture.supplyAsync(() -> {
            assessDao.addComment(comment);
            return AddCommentResponse.builder()
                    .commentId(comment.getId())
                    .build();
        },executor);
    }

    @Override
    public void assess(List<Assess> assessList, Integer projectId, Integer markerId, Integer studentId, Integer groupId, String assessedDate) {
        for (int i = 0; i < assessList.size(); i++) {

            Integer criteriaId = assessList.get(i).getCriteriaId();
            String comment = assessList.get(i).getComment();
            Double score = assessList.get(i).getScore();
            assessDao.setIndividualScore(projectId, markerId, studentId, groupId, criteriaId, comment, score, assessedDate);
        }
    }

    @Override
    public CompletableFuture<GetCommentResponse> getComment(Integer markerId) {
        CompletableFuture<List<Comment>> future = CompletableFuture.supplyAsync(() -> assessDao.getCommentList(markerId));
        return future.thenApplyAsync(comments -> {
            log.info("Marker {}'s comments load", markerId);
            return GetCommentResponse.builder()
                    .comments(comments).build();
        }, executor);
    }
}
