package per.yxtech;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

@WebServlet(urlPatterns = {"/servletdemo.do"}, loadOnStartup = 0, name = "servletdemo", displayName = "demo",
        initParams = {
            @WebInitParam(name="dir",  value="/dir"),
                @WebInitParam(name="success", value="success.htm"),
                @WebInitParam(name="resourcePath", value="/dir/test.txt")
        })
public class ServletInfo extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException,IOException{
        doPost(req, rep);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException{

        String dir = getInitParameter("dir");
        String success = getInitParameter("success");
        String resourcePath = getInitParameter("resourcePath");

        ServletContext context = getServletContext();
        String path = context.getRealPath(success);
        System.out.println("path真实路径--"+path);
        Set set = context.getResourcePaths(dir);
        for(Object str:set){
            System.out.println("文件内容----"+(String)str);
        }

        String serverInfo = context.getServerInfo();
        System.out.println("服务器版本："+serverInfo);
        InputStream in = context.getResourceAsStream(resourcePath);
        OutputStream os = rep.getOutputStream();

        byte[] buffer = new byte[1024];
        while (in.read(buffer) != -1){
            os.write(buffer);
        }

        in.close();
        os.close();
    }

}
