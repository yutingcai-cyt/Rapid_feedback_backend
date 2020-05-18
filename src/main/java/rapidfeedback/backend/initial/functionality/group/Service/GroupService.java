package rapidfeedback.backend.initial.functionality.group.Service;


import rapidfeedback.backend.initial.functionality.group.model.StudentId;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author cyt
 * @date 2020/5/4
 */
public interface GroupService {

    /**
     * create a group which means add some students into one group
     * @param projectId
     * @param group
     * @param studentList
     * @return
     */
    CompletableFuture<Void> addStudentsIntoSingleGroup(Integer projectId, Integer group, List<StudentId> studentList);

    /**
     * delete a group which means delete some students from a group
     * @param projectId
     * @param group
     * @return
     */
    CompletableFuture<Void> deleteGroup(Integer projectId, Integer group);

}
