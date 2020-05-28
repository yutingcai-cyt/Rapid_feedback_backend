package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rapidfeedback.backend.initial.CommonTools.JsonTool.JsonTransfer;
import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.assess.model.*;
import rapidfeedback.backend.initial.functionality.assess.service.AssessService;
import rapidfeedback.backend.initial.model.Assess;
import rapidfeedback.backend.initial.model.Comment;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RestController
@RequestMapping("/v1")
public class AssessController {

    @Autowired
    private AssessService assessService;

    @Resource(name = "controllerThreadPool")
    private ThreadPoolExecutor executor;

    @GetMapping("/result/{projectId}/{studentId}")
    public CompletableFuture<ResponseEntity<GetResultResponse>> getIndividualResult(HttpServletRequest request, @PathVariable("projectId") Integer projectId, @PathVariable("studentId") Integer studentId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return assessService.getIndividualResult(projectId, studentId)
                .thenApplyAsync(getResultResponse -> {
                    log.info("Student {}'s assessment", studentId);
                    return ResponseEntity.ok(getResultResponse);
                },executor);
    }


    @PostMapping("/assess")
    public void assess(HttpServletRequest request, @RequestBody AssessRequest assessRequest){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        List<Assess> assessList = assessRequest.getAssessList();
        assessService.assess(assessList, assessRequest.getProjectId(), assessRequest.getMarkerId(), assessRequest.getStudentId(), assessRequest.getGroupId(), assessRequest.getAssessedDate());

        log.info("assess student{}", assessRequest.getStudentId());
    }

    @PostMapping("/addComment")
    public CompletableFuture<ResponseEntity<AddCommentResponse>> addComment(HttpServletRequest request, @RequestBody AddCommentRequest addCommentRequest){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        Comment comment = JsonTransfer.transfer(addCommentRequest, new Comment());
        return assessService.addComment(comment)
                .thenApplyAsync(addCommentResponse -> {
                    log.info("Add comment {} into marker {}", comment.getId(), comment.getMarkerId());
                    return ResponseEntity.ok(addCommentResponse);
                },executor);
    }

    @GetMapping("/comments/{markerId}")
    public CompletableFuture<ResponseEntity<GetCommentResponse>> getComments(HttpServletRequest request, @PathVariable("markerId") Integer markerId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request, token);
        return assessService.getComment(markerId)
                .thenApplyAsync(getCommentResponse -> {
                    log.info("user {}'s comments list", markerId);
                    return ResponseEntity.ok(getCommentResponse);
                },executor);
    }
}
