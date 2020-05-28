package rapidfeedback.backend.initial.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import rapidfeedback.backend.initial.CommonTools.Exception.CommonError;
import rapidfeedback.backend.initial.CommonTools.Exception.FBException;
import rapidfeedback.backend.initial.CommonTools.JsonTool.JsonTransfer;
import rapidfeedback.backend.initial.CommonTools.Token.Token;
import rapidfeedback.backend.initial.functionality.student.Service.StudentService;
import rapidfeedback.backend.initial.functionality.student.model.AddStudentRequest;
import rapidfeedback.backend.initial.functionality.student.model.AddStudentResponse;
import rapidfeedback.backend.initial.functionality.student.model.BatchAddStudentRequest;
import rapidfeedback.backend.initial.functionality.student.model.getStudentResponse;
import rapidfeedback.backend.initial.model.Student;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.*;
import java.util.List;
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

    private static final String PATH ="/";

    @GetMapping("/template")
    public ResponseEntity<ByteArrayResource> getTemplate(HttpServletRequest request, HttpServletResponse response){

//        File file = new File(PATH);
//        if(!file.exists()){
//            throw new FBException(CommonError.INTERNAL_SERVER_ERROR.getResultCode(),"template losed!");
//        }
        byte[] buffer = null;
        try{
            /**
             * find the file from resources
             */
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=template.xlsx");
            InputStream inputStream = StudentController.class.getClassLoader().getResourceAsStream("template.xlsx");
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while((n = inputStream.read(b)) != -1){
                bos.write(b,0,n);
            }
            inputStream.close();
            bos.close();
            buffer = bos.toByteArray();
            return new ResponseEntity<>(new ByteArrayResource(buffer),header, HttpStatus.ACCEPTED);


        }catch (Exception  e){
            throw new FBException(CommonError.INTERNAL_SERVER_ERROR.getResultCode(),e.getMessage());


        }

    }

    @PostMapping("")
    public CompletableFuture<ResponseEntity<AddStudentResponse>> addStudentIntoProejct(HttpServletRequest request, @RequestBody AddStudentRequest addStudentRequest) {
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request,token);
        Student student = JsonTransfer.transfer(addStudentRequest,new Student());
        log.info("studentInfo {}",student);
        return studentService.addStudent(student,addStudentRequest.getProject_id())
                .thenApplyAsync(addStudentResponse -> {
                    log.info("add student {} into project {}", addStudentResponse.getStudent_id(),addStudentResponse.getProject_id());
                    return ResponseEntity.ok(addStudentResponse);
                },executor);
    }

    @PostMapping("/batch")
    public CompletableFuture<ResponseEntity<List<AddStudentResponse>>> batchAddStudentIntoProject(HttpServletRequest request, @RequestBody BatchAddStudentRequest batchAddStudentRequest){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request,token);
        return studentService.batchAddStudents(batchAddStudentRequest.getStudentList(),batchAddStudentRequest.getProject_id())
                .thenApplyAsync(response -> {
                    log.info("add students {} into project with id {}",batchAddStudentRequest.getStudentList(),batchAddStudentRequest.getProject_id());
                    return ResponseEntity.ok(response);
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

    @GetMapping("/{projectId}")
    public CompletableFuture<ResponseEntity<List<getStudentResponse>>> getStudentsInProject(HttpServletRequest request, @PathVariable(name = "projectId") Integer projectId){
        String token = request.getHeader("Authorization");
        Token.tokenCheck(request,token);
        return studentService.getStudentdsInProject(projectId).thenApplyAsync(list -> {
            log.info("get all students {} from the project with id {}",list,projectId);
            return ResponseEntity.ok(list);
        });
    }
}