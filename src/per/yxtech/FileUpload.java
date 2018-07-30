package per.yxtech;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;

@MultipartConfig(location = "D:/tmp/",maxFileSize = 1024*1024*10)
@WebServlet(
        name="fileUpload",
        urlPatterns = { "/fileupload" },
        loadOnStartup = 0
)
public class FileUpload extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Part part = req.getPart("filename");
        String fileName = getFileName(part);
        part.write(fileName);
        rep.setContentType("text/html;charset=utf-8");
        PrintWriter out = rep.getWriter();
        out.println("<html><body>上传成功</body></html>");
        out.flush();
        out.close();
        /*
        byte[] body = readBody(req);

        String txtBody = new String(body, "UTF-8");
        String fileName = getFileName(txtBody);
        writeToFile(fileName, body);
        */
    }

    private String getFileName(Part part) {
        if(part == null) return null;
        String filename = part.getHeader("content-disposition");
        if(org.apache.commons.lang3.StringUtils.isBlank(filename)) return null;
        return org.apache.commons.lang3.StringUtils.substringBetween(filename, "filename=\"","\"");

    }

    private void writeToFile(String fileName, byte[] body) throws IOException {
        try {
            FileOutputStream stream = new FileOutputStream("d:\\" + fileName);
            stream.write(body);
            stream.flush();
            stream.close();
        }catch (FileAlreadyExistsException ex){
            System.out.println("文件已存在："+ex.getMessage());
        }
    }

    private String getFileName(String txtBody) {
        String filename = txtBody.substring(txtBody.indexOf("filename=\"")+10);
        filename =  filename.substring(filename.lastIndexOf("\\")+1, filename.indexOf("\""));
        return filename;
    }

    private byte[] readBody(HttpServletRequest request) throws IOException{
        int len = request.getContentLength();
        InputStream stream = request.getInputStream();
        byte[] b = new byte[len];
        int total =0;
        while(total<len){
            int bytes = stream.read(b, total, len);
            total+=bytes;
        }

        return b;
    }
}
