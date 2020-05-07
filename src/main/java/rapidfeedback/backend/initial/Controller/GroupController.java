package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.group.Service.GroupService;
import rapidfeedback.backend.initial.functionality.group.model.CreateGroupRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author cyt
 * @date 2020/5/4
 */

@Slf4j
@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @Resource(name = "controllerThreadPool")
    private ThreadPoolExecutor executor;

    @Transactional
    @PostMapping()
    public CompletableFuture<ResponseEntity<Void>> createSingleGroup(HttpServletRequest request, @RequestBody CreateGroupRequest createGroupRequest){

        String token = request.getHeader("Authorization");
        Token.tokenCheck(request,token);
        return groupService.addStudentsIntoSingleGroup(createGroupRequest.getProject_id(),createGroupRequest.getGroup_id(),createGroupRequest.getStudentList())
        .thenApplyAsync(aVoid -> {
            log.info("create group {} in project {}", createGroupRequest.getProject_id(),createGroupRequest.getProject_id());
            return ResponseEntity.ok(aVoid);
        },executor);
    }

    @DeleteMapping("/{projectId}/{groupId}")
    public CompletableFuture<ResponseEntity<Void>> deleteGroup(HttpServletRequest request, @PathVariable(name ="projectId")Integer projectId, @PathVariable(name="groupId")Integer group ){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request,token);
        return groupService.deleteGroup(projectId,group).thenApplyAsync(aVoid -> {
            log.info("delete group {}",group);
            return ResponseEntity.ok(aVoid);
        },executor);
    }
}
