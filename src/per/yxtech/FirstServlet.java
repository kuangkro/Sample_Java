package per.yxtech;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {

    public void init() throws ServletException{
        System.out.println("调用 init 方法");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        System.out.println("调用 doGet 方法");
        response.setContentType("text/hmlt;charset=gbk");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>测试0-10的循环结果</title></head>");
        out.println("<body>");
        out.println("开始执行");
        int cnt =0;
        for(int i=0; i<10; i++){
            cnt+=i;
        }
        out.println("程序执行结果："+cnt);
        out.println("</body>");
        out.println("</html>");

        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        doGet(request, response);
    }

    public void destory(){
        System.out.println("调用 destory 方法");
    }
}
