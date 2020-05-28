package rapidfeedback.backend.initial.functionality.updateProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.CommonTools.JsonTool.JsonTransfer;
import rapidfeedback.backend.initial.functionality.createProject.model.CreateProjResponse;
import rapidfeedback.backend.initial.functionality.updateProject.dao.UpdateProjectDao;
import rapidfeedback.backend.initial.functionality.updateProject.model.AddCriteriaResponse;
import rapidfeedback.backend.initial.functionality.updateProject.model.GetCriteriaResponse;
import rapidfeedback.backend.initial.functionality.updateProject.model.GetMarkerResponse;
import rapidfeedback.backend.initial.model.Criteria;
import rapidfeedback.backend.initial.model.CriteriaContent;
import rapidfeedback.backend.initial.model.CriteriaData;
import rapidfeedback.backend.initial.model.Project;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
public class UpdateProjectService implements UpdateProjService{

    @Autowired
    UpdateProjectDao updateProjectDao;

    @Resource(name = "serviceThreadPool")
    private ThreadPoolExecutor executor;

    @Override
    public CompletableFuture<CreateProjResponse> updateProject(Project project, Integer projectId){

        return CompletableFuture.supplyAsync(() -> {
            updateProjectDao.updateProject(project, projectId);
            return CreateProjResponse.builder()
                    .id(projectId)
                    .subject_code(project.getSubject_code())
                    .subject_name(project.getSubject_name())
                    .proj_name(project.getProj_name())
                    .duration(project.getDuration())
                    .due_date(project.getDue_date())
                    .is_group(project.getIs_group())
                    .proj_description(project.getProj_description())
                    .build();
        },executor);
    }

    @Override
    public void addMarker(List<Integer> markerIdList, Integer projectId) {
        for (int i = 0; i < markerIdList.size(); i++) {
            Integer markerId = markerIdList.get(i);
            updateProjectDao.addMarker(markerId, projectId);
        }
    }

    @Override
    public CompletableFuture<GetMarkerResponse> getMarker(Integer projectId){
        CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> updateProjectDao.getMarker(projectId));
        return future.thenApplyAsync(marker -> {
            log.info("Criteria load");
            return GetMarkerResponse.builder()
                    .markerIdList(marker).build();
        }, executor);
    }

    @Override
    public void updateCriteria(Integer projectId, List<Criteria> criteriaList) {
        for (int i = 0; i < criteriaList.size(); i++) {
            updateProjectDao.updateCriteria(projectId, criteriaList.get(i).getCriteriaId(), criteriaList.get(i).getWeight());
        }
        log.info("updated");
    }

    @Override
    public CompletableFuture<GetCriteriaResponse> getCriteria(Integer projectId) {
        CompletableFuture<List<CriteriaContent>> future = CompletableFuture.supplyAsync(() -> updateProjectDao.getCriteriaList(projectId));
        return future.thenApplyAsync(criteria -> {
            log.info("Criteria load");
            return GetCriteriaResponse.builder()
                    .criteriaList(criteria).build();
        }, executor);
    }

    @Override
    public CompletableFuture<AddCriteriaResponse> addCriteria(Integer projectId, CriteriaData criteria, Integer weight) {
        return CompletableFuture.supplyAsync(() -> {
            updateProjectDao.addCriteria(criteria);
            updateProjectDao.addCriteriaIntoProject(projectId, criteria.getId(), weight);
            return AddCriteriaResponse.builder()
                    .criteriaId(criteria.getId())
                    .build();
        },executor);
    }

}
