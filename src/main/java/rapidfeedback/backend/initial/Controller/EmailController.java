package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rapidfeedback.backend.initial.functionality.email.Service.EmailService;
import rapidfeedback.backend.initial.functionality.email.model.EmailRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author cyt
 * @date 2020/5/28
 */
@RestController
@RequestMapping("/v1/result")
@Slf4j
public class EmailController {


    @Autowired
    EmailService emailService;

    @PostMapping("/email")
    public CompletableFuture<ResponseEntity<Void>> sendEmail(HttpServletRequest request, @RequestBody EmailRequest emailRequest){
//        String token = request.getHeader("Authorization");
//        Token.tokenCheck(request,token);
        return emailService.sendEmail(emailRequest.getProject_id(),emailRequest.getStudentIdList(),emailRequest.getOption())
                .thenApplyAsync(aVoid -> {
                    log.info("send email for project "+emailRequest.getProject_id());
                    return ResponseEntity.ok(aVoid);
                });
    }

    @PostMapping("/audio")
    public CompletableFuture<ResponseEntity<Void>> uploadAudio(HttpServletRequest request, @RequestParam("audio")MultipartFile file, @RequestParam("project_id") Integer projectId, @RequestParam("studentIdList")List<Integer> studentIdList){
//        String token = request.getHeader("Authorization");
//        Token.tokenCheck(request,token);
        return emailService.addAudio(projectId,studentIdList,file).thenApplyAsync(aVoid ->{
           log.info("save audio for porject {} and student {}",projectId,studentIdList);
           return ResponseEntity.ok(aVoid);
        });
    }
}
