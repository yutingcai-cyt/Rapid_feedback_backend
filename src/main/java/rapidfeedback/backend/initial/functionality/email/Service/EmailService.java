package rapidfeedback.backend.initial.functionality.email.Service;

import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rapidfeedback.backend.initial.CommonTools.CompletableFuture.CompletableFutureTool;
import rapidfeedback.backend.initial.CommonTools.Exception.CommonError;
import rapidfeedback.backend.initial.CommonTools.Exception.FBException;
import rapidfeedback.backend.initial.CommonTools.PDFTool.PDFTool;
import rapidfeedback.backend.initial.CommonTools.emailTools.EmailInfo;
import rapidfeedback.backend.initial.CommonTools.emailTools.EmailTool;
import rapidfeedback.backend.initial.functionality.email.Dao.EmailDao;
import rapidfeedback.backend.initial.functionality.email.model.Assessment;
import rapidfeedback.backend.initial.functionality.email.model.AudioRequest;
import rapidfeedback.backend.initial.functionality.email.model.CriteriaInfo;
import rapidfeedback.backend.initial.model.Marker;
import rapidfeedback.backend.initial.model.Project;
import rapidfeedback.backend.initial.model.Student;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author cyt
 * @date 2020/5/28
 */

@Service
@Slf4j
public class EmailService {

    @Autowired
    EmailTool emailTool;

    @Autowired
    PDFTool pdfTool;

    @Autowired
    EmailDao emailDao;
    //private static final String PATH ="D:\\unimelb最后一学期\\softwareProject\\backend\\henfan\\Rapid_feedback_backend\\test.pdf";
    private static final String PATH ="/tmp/test.pdf";

    public CompletableFuture<Void> sendEmail(Integer projectId, List<Integer> studentIdList, Integer option){
        return CompletableFuture.runAsync(() ->{
            /**
             * create PDF
             */
            Project project = emailDao.getProjectInfo(projectId);
            List<Student> studentList = studentIdList.stream()
                    .map(studentId -> emailDao.getStudentById(studentId))
                    .collect(Collectors.toList());
            Marker coordinator = emailDao.getCoordinator(projectId);
            if(studentIdList.size() == 0){
                throw new FBException(CommonError.BAD_REQUEST.getResultCode(),"there is no student information");
            }
            List<Assessment> assessments = emailDao.getAssessmentResult(projectId,studentIdList.get(0),coordinator.getId());
            log.info(assessments+"");
            List<CriteriaInfo> criteriaInfoList = assessments.stream()
                    .map(assessment -> {

                        CriteriaInfo criteriaInfo = emailDao.getCriteriaInfo(projectId,assessment.getCriteria_id());
                        criteriaInfo.setScore(assessment.getScore());
                        return criteriaInfo;
                    })
                    .collect(Collectors.toList());
            log.info(criteriaInfoList+"");
            pdfTool.createPDF(PATH,project,studentList,coordinator,assessments,criteriaInfoList);


            /**
             * get audio
             */
            AudioRequest audioRequest = emailDao.getAudio(coordinator.getId(),projectId,studentList.get(0).getId());

            /**
             * send email, if option = 1, send only to the all markers of this project; if 2, send to the students
             * if 3, send to both markers and students
             */
            List<Marker> markerList = emailDao.getAllMarkersOfProject(projectId);
            log.info(markerList+"");
            List<String> markerEmails = markerList.stream()
                    .map(marker -> marker.getUni_email())
                    .collect(Collectors.toList());
            Double totalScore = 0d;
            Double totalWeight = 0d;
            for(CriteriaInfo c: criteriaInfoList){
                totalScore += c.getScore();
                totalWeight += c.getWeight();
            }
            if(option < 1 || option > 3){
                throw new FBException(CommonError.BAD_REQUEST.getResultCode(),"option can only be 1,2,3");
            }
            //only send to markers
            if(option == 1){
                emailTool.sendMimeMessage(markerEmails,project.getSubject_name(), EmailInfo.createEmailContent(project.getSubject_code()
                        ,project.getSubject_name(),project.getProj_name(),totalWeight,totalScore),PATH,audioRequest);
            }

            List<String> studentEmails = studentList.stream()
                    .map(student -> student.getUni_email())
                    .collect(Collectors.toList());
            //only send to students
            if(option == 2){
                emailTool.sendMimeMessage(studentEmails,project.getSubject_name(), EmailInfo.createEmailContent(project.getSubject_code()
                        ,project.getSubject_name(),project.getProj_name(),totalWeight,totalScore),PATH,audioRequest);
            }
            log.info(markerEmails+"");
            log.info(studentEmails+"");
            markerEmails.addAll(studentEmails);
            //send to both students and markers
            log.info(markerEmails+"");
            if(option == 3){
                emailTool.sendMimeMessage(markerEmails,project.getSubject_name(), EmailInfo.createEmailContent(project.getSubject_code()
                        ,project.getSubject_name(),project.getProj_name(),totalWeight,totalScore),PATH,audioRequest);
            }

        });

    }

    public CompletableFuture<Void> addAudioForSingleStudent(Integer markerId,Integer studentId, Integer projectId, MultipartFile file, String type){
        return CompletableFuture.runAsync(() ->{
            try{
                AudioRequest audioRequest = new AudioRequest();
                byte[] test = file.getBytes();
                log.info(test.length+"");
                audioRequest.setBin_data(file.getBytes());
                AudioRequest data = emailDao.getAudio(markerId,projectId,studentId);
                if(data != null && data.getBin_data() != null){
                    emailDao.updateAudio(audioRequest.getBin_data(),studentId,markerId,projectId);
                }else{
                    emailDao.addAudio(projectId,studentId,markerId,audioRequest.getBin_data(),type);
                }

            }catch (IOException e){
                throw new FBException(CommonError.INTERNAL_SERVER_ERROR.getResultCode(),"file has something wrong");
            }

        });

    }


    public CompletableFuture<Void> addAudio(Integer projectId, List<Integer> studentIdList, MultipartFile file){
        Marker coordinator = emailDao.getCoordinator(projectId);
        String fileName = file.getOriginalFilename();
        int pointIndex = fileName.lastIndexOf(".");
        String type = fileName.substring(pointIndex+1);
        List<CompletableFuture<Void>> futures = studentIdList.parallelStream()
                .map(studentId -> addAudioForSingleStudent(coordinator.getId(),studentId,projectId,file,type))
                .collect(Collectors.toList());
        return CompletableFutureTool.allOf(futures).thenRunAsync(() ->{});
    }





}
