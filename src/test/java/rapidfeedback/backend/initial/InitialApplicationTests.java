package rapidfeedback.backend.initial;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rapidfeedback.backend.initial.CommonTools.PDFTool.PDFTool;
import rapidfeedback.backend.initial.CommonTools.emailTools.EmailInfo;
import rapidfeedback.backend.initial.CommonTools.emailTools.EmailTool;
import rapidfeedback.backend.initial.functionality.createProject.dao.CreateProjectDao;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
class InitialApplicationTests {

	@Autowired
	private EmailTool emailTool;

	@Autowired
	private PDFTool pdfTool;

	@Autowired
	private CreateProjectDao createProjectDao;
//	@Test
//	public void emailTest(){
//		emailTool.sendMimeMessage("yutingcaicyt@gmail.com","test", EmailInfo.createEmailContent(),"D:\\unimelb最后一学期\\softwareProject\\backend\\henfan\\Rapid_feedback_backend\\test.pdf");
//
//	}
//
//
//
//	@Test
//	public void PDFTest(){
//		pdfTool.createPDF("D:\\unimelb最后一学期\\softwareProject\\backend\\henfan\\Rapid_feedback_backend\\test.pdf",createProjectDao.getProjectById(1));
//	}



}
