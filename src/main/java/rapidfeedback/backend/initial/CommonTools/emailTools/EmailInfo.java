package rapidfeedback.backend.initial.CommonTools.emailTools;

import lombok.Data;
import rapidfeedback.backend.initial.model.Project;

/**
 * @author cyt
 * @date 2020/5/25
 */
@Data
public class EmailInfo {
    public static String  createEmailContent(){
        return "Dear COMP90082 Software Project {SubjectCode&SubjectName } student,<br>" +

                "Attached is your mark breakdown and feedback for Project1 {ProjectName}. The assignment was marked out of  39 {yourMark}," +
                "and it makes up 40 {maxMark} of your final subject mark. <br>" +

                "Please contact us if you have any further questions about your mark or feedback.<br>" +

                "Best wishes,<br>" +
                "The COMP90082 Software Project {SubjectCode&SubjectName} team";
    }
}
