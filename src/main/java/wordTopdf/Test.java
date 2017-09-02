package wordTopdf;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by tianhe on 2017/8/9.
 */
public class Test {
    public static boolean getLicense() {
        boolean result = false;
        try {
            File licenseFile = new File("/Users/tianhe/IdeaProjects/java_example/src/main/java/wordTopdf/license.xml");
            InputStream is = new FileInputStream(licenseFile); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void doc2pdf(String Address, String name) {
//        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
//            return;
//        }
        try {
            long old = System.currentTimeMillis();
            String pdfName = name.substring(0, name.indexOf(".")+1)+ "pdf";
            File file = new File(pdfName);  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(Address+"/"+file);
            Document doc = new Document(Address);                    //Address是将要被转化的word文档
            if(doc == null){
                System.out.println("cannot parse word file");
                return;
            }
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String name = "/Users/tianhe/IdeaProjects/java_example/src/main/java/wordTopdf/a.docx";
//        String c = name.substring(0, name.indexOf(".")+1)+ "pdf";
//        int Year = 			LocalDate.now().getYear();
//        int month;
//        String monthString =  (month = LocalDate.now().getMonthValue()) <10 ? "0"+month: ""+month ;
//        System.out.println(Year+ monthString);
//        System.out.println();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-n");
        String result = localDateTime.format(dtf);
        System.out.println(result);
        String t = "abc.doc";
        String suffix = t.substring(t.indexOf('.'), t.length());
        System.out.println(suffix);

    }
}
