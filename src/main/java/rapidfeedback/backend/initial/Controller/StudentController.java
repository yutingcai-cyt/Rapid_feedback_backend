package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.student.Service.StudentService;
import rapidfeedback.backend.initial.functionality.student.model.AddStudentRequest;
import rapidfeedback.backend.initial.functionality.student.model.AddStudentResponse;
import rapidfeedback.backend.initial.model.Student;

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
@RequestMapping("/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Resource(name = "controllerThreadPool")
    private ThreadPoolExecutor executor;

    @PostMapping("")
    public CompletableFuture<ResponseEntity<AddStudentResponse>> addStudentIntoProejct(HttpServletRequest request, @RequestBody AddStudentRequest addStudentRequest) {
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request,token);
        Student student = new Student();
        student.setFirst_name(addStudentRequest.getFirst_name());
        student.setLast_name(addStudentRequest.getLast_name());
        student.setUni_email(addStudentRequest.getUni_email());
        student.setUni_student_number(addStudentRequest.getUni_student_number());


        return studentService.addStudent(student,addStudentRequest.getProject_id())
                .thenApplyAsync(addStudentResponse -> {
                    log.info("add student {} into project {}", addStudentResponse.getStudent_id(),addStudentResponse.getProject_id());
                    return ResponseEntity.ok(addStudentResponse);
                },executor);
    }

    @DeleteMapping("/{studentId}/{projectId}")
    public CompletableFuture<ResponseEntity<Void>> deleteStudentFromProject(HttpServletRequest request, @PathVariable(name = "studentId") Integer studentId, @PathVariable(name = "projectId") Integer projectId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request,token);
        return studentService.deleteStudentFromProject(studentId,projectId).thenApplyAsync(aVoid ->{
            log.info("delete student {} from project {}", studentId,projectId );
            return ResponseEntity.ok(aVoid);
        },executor);
    }
}