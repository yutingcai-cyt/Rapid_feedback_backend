package rapidfeedback.backend.initial.functionality.student.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.CommonTools.CompletableFuture.CompletableFutureTool;
import rapidfeedback.backend.initial.CommonTools.JsonTool.JsonTransfer;
import rapidfeedback.backend.initial.functionality.student.Dao.StudentDao;
import rapidfeedback.backend.initial.functionality.student.model.AddStudentResponse;
import rapidfeedback.backend.initial.functionality.student.model.getStudentResponse;
import rapidfeedback.backend.initial.model.Student;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author cyt
 * @date 2020/5/4
 */

@Slf4j
@Service
public class StudentServiceImpl implements  StudentService{

    @Autowired
    private StudentDao studentDao;


    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;


    @Override
    public CompletableFuture<List<AddStudentResponse>> batchAddStudents(List<Student> studentList, Integer projectId) {
        List<CompletableFuture<AddStudentResponse>> futureList = studentList.parallelStream()
                .map(student -> addStudent(student,projectId))
                .collect(Collectors.toList());
        return CompletableFutureTool.allOf(futureList);
    }


    @Override
    public CompletableFuture<AddStudentResponse> addStudent(Student student, Integer projectId) {
        CompletableFuture<Student> future = CompletableFuture
                .supplyAsync(() -> studentDao.getStudentByStudentNumber(student.getUni_student_number()));
        return future.thenApplyAsync(student1 -> {
           if(student1 == null){
               log.info("the student dose not exist in student table!");
               studentDao.addStudent(student);
           }else{
               log.info("this student already exist in student table");
               student.setId(student1.getId());
           }
            log.info("studentInfo: {}",student);
           studentDao.addStudentIntoProject(student.getId(),projectId);
           AddStudentResponse response = JsonTransfer.transfer(student,new AddStudentResponse());
           response.setProject_id(projectId);
           response.setStudent_id(student.getId());
           return response;
        },executor);
    }




    @Override
    public CompletableFuture<Void> deleteStudentFromProject(Integer studentId, Integer projectId) {
        return CompletableFuture.runAsync(() -> studentDao.deleteStudent(studentId,projectId),executor);
    }

    @Override
    public CompletableFuture<List<getStudentResponse>> getStudentdsInProject(Integer projectId) {
        return CompletableFuture.supplyAsync(() -> studentDao.getStudentsByProjectId(projectId));
    }


}
