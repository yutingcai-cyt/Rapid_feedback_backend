package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.email.Service.EmailService;
import rapidfeedback.backend.initial.functionality.email.model.EmailRequest;

import javax.servlet.http.HttpServletRequest;
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
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request,token);
        return emailService.sendEmail(emailRequest.getProject_id(),emailRequest.getStudentIdList(),emailRequest.getOption())
                .thenApplyAsync(aVoid -> {
                    log.info("send email for project "+emailRequest.getProject_id());
                    return ResponseEntity.ok(aVoid);
                });
    }
}
