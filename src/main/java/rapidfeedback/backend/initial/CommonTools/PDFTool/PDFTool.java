package rapidfeedback.backend.initial.CommonTools.PDFTool;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.CommonTools.Exception.CommonError;
import rapidfeedback.backend.initial.CommonTools.Exception.FBException;
import rapidfeedback.backend.initial.model.Project;
import rapidfeedback.backend.initial.model.Student;


import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author cyt
 * @date 2020/5/16
 */
@Service
public class PDFTool {

    public void createPDF(String filename, Project project){
        Document document = new Document(PageSize.A4,31.8f,31.8f,25.4f,25.4f);

        try{

            PdfWriter.getInstance(document,new FileOutputStream(filename));

            document.addTitle("example of PDF");
            document.open();
            // generate the title of PDF
            Paragraph title = new Paragraph("Presentation Assessment",FontFactory.getFont(FontFactory.HELVETICA_BOLD,12));
            title.setAlignment(Element.ALIGN_CENTER);
            title.setIndentationLeft(50);
            document.add(title);

            Paragraph subjectInfo = new Paragraph("\n \n \n Subject Name: "+project.getSubject_name(),FontFactory.getFont(FontFactory.HELVETICA_BOLD,10));
            subjectInfo.setIndentationLeft(50);
            document.add(subjectInfo);

            Paragraph projectInfo = new Paragraph("\n Project Name: "+project.getProj_name(),FontFactory.getFont(FontFactory.HELVETICA_BOLD,10));
            projectInfo.setIndentationLeft(50);
            document.add(projectInfo);

            Paragraph coordinator = new Paragraph("\n Coordinator: "+"Leon",FontFactory.getFont(FontFactory.HELVETICA_BOLD,10));
            coordinator.setIndentationLeft(50);
            document.add(coordinator);

            Paragraph mark = new Paragraph("\n Overall Result: "+"20.0/40.0", FontFactory.getFont(FontFactory.HELVETICA_BOLD,10));
            mark.setIndentationLeft(50);
            document.add(mark);

            Paragraph comment = new Paragraph("\n Comments: \n \n", FontFactory.getFont(FontFactory.HELVETICA_BOLD,10));
            comment.setIndentationLeft(50);
            document.add(comment);

//            Paragraph criteria = new Paragraph(" \n "+"voice                                                                                                        " +
//                    "   "+"6/10 \n \n");
//            criteria.setIndentationLeft(50);
//            document.add(criteria);
//            PdfPTable table1 = new PdfPTable(1);
//            PdfPCell cell;
//            cell = new PdfPCell();
//            cell.addElement(new Phrase("marker1: "+"\n there is a comment for the criteria"));
//            cell.addElement(new Phrase("\nmarker2: "+"\nthere is the comment for the criteria"));
//            table1.addCell(cell);
            List<String> temp = new ArrayList<>();
            temp.add("vcxvxc");
            temp.add("nbnnbvbv");
            createTable(document, "voice", 40, 20, temp);
            createTable(document, "fsdf", 40, 20, temp);
        }catch (Exception e){
            throw new FBException(CommonError.INTERNAL_SERVER_ERROR.getResultCode(),e.getMessage());

        }finally {
            document.close();
        }
    }
    public void createTable(Document document,String criteria, Integer totalMark, Integer mark, List<String> comments) throws Exception{
        PdfPTable table = new PdfPTable(1);
        PdfPCell cell1 = new PdfPCell();
        cell1.addElement(new Chunk(criteria+" " +"\nscore: "+mark+"/"+totalMark));
        table.addCell(cell1);
        PdfPCell cell2;
        cell2 = new PdfPCell();
        int count = 0;
        for(String c : comments) {
            count += 1;
            cell2.addElement(new Phrase("marker" + count + ": \n" + c + "\n"));
        }
        table.addCell(cell2);
        document.add(table);
        document.add(new Chunk("\n"));
    }

}
