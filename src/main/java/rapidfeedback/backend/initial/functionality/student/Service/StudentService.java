package rapidfeedback.backend.initial.functionality.student.Service;

import rapidfeedback.backend.initial.functionality.student.model.AddStudentRequest;
import rapidfeedback.backend.initial.functionality.student.model.AddStudentResponse;
import rapidfeedback.backend.initial.functionality.student.model.getStudentResponse;
import rapidfeedback.backend.initial.model.Student;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author cyt
 * @date 2020/5/4
 */
public interface StudentService {

    /**
     * add a student into the project, if this student dosen't exist in student table,
     * the student will be created firstly, and then add into a project;
     * @param student
     * @return student
     */
    CompletableFuture<AddStudentResponse> addStudent(Student student, Integer projectId);

    /**
     * delete a student from a project but not the student table
     * @param studentId
     * @param projectId
     * @return
     */
    CompletableFuture<Void> deleteStudentFromProject(Integer studentId, Integer projectId);


    /**
     * get all students who are in a single project
     * @param projectId
     * @return List<Student>
     */
    CompletableFuture<List<getStudentResponse>> getStudentdsInProject(Integer projectId);
}
