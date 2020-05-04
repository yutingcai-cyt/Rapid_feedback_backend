package rapidfeedback.backend.initial.functionality.group.Service;

import com.sun.xml.internal.ws.util.CompletedFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.CommonTools.CompletableFuture.CompletableFutureTool;
import rapidfeedback.backend.initial.CommonTools.Exception.CommonError;
import rapidfeedback.backend.initial.CommonTools.Exception.FBException;
import rapidfeedback.backend.initial.functionality.group.Dao.GroupDao;
import rapidfeedback.backend.initial.functionality.group.model.StudentId;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author cyt
 * @date 2020/5/4
 */

@Service
@Slf4j
public class GroupServiceImpl implements  GroupService {

    @Autowired
    private GroupDao groupDao;


    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;

    @Override
    public CompletableFuture<Void> addStudentsIntoSingleGroup(Integer projectId, Integer group, List<StudentId> studentList) {
        List<CompletableFuture<Void>> futures = studentList.parallelStream()
                .map(studentId -> CompletableFuture.runAsync(() -> groupDao.addStudentIntoGroup(studentId.getStudent_id(), projectId, group)))
                .collect(Collectors.toList());

        return CompletableFutureTool.allOf(futures).thenRunAsync(() -> {},executor).exceptionally(throwable -> {
            throw new FBException(CommonError.BAD_REQUEST.getResultCode(), throwable.getCause().getMessage());
        });

    }

    @Override
    public CompletableFuture<Void> deleteGroup(Integer projectId, Integer group) {
        return CompletableFuture.runAsync(() -> groupDao.deleteGroup(projectId, group),executor);
    }

}
