package per.yxtech;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    //private static Logger log=Logger.getLogger("ServletContextListener");

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String name = context.getInitParameter("user_name");
        //log.debug("初始化name参数："+name);
        //log.debug("Tomcat启动中...");
        System.out.println("初始化name参数："+name);
        System.out.println("Tomcat启动中...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //log.debug("Tomcat关闭中...");
        System.out.println("Tomcat关闭中...");
    }
}
