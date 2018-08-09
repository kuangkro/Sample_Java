package per.yxtech;

import com.helper.ResponseReplaceWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebFilter(description = "内容过滤", filterName = "replace_filter", urlPatterns = {"/*"}, initParams = { @WebInitParam(name = "filePath", value="properties/replace_ZH.properties")})
public class ReplaceFilter implements Filter {

    private Properties propert = new Properties();
    private String filterName;

    public void init(FilterConfig config) throws ServletException{
        filterName = config.getFilterName();
        String filePath = config.getInitParameter("filePath");
        try{
            propert.load(ReplaceFilter.class.getClassLoader().getResourceAsStream(filePath));

            //propert.load(new FileInputStream(filePath));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("请求被"+filterName+"过滤");
        HttpServletResponse rep = (HttpServletResponse)servletResponse;
        ResponseReplaceWrapper resp = new ResponseReplaceWrapper(rep);
        filterChain.doFilter(servletRequest, resp);

        String outString = resp.getCharWriter().toString();
        for(Object o:propert.keySet()){
            String key = (String)o;
            outString = outString.replace(key, propert.getProperty(key));
        }

        PrintWriter writer = rep.getWriter();
        writer.write(outString);
    }

    @Override
    public void destroy() {

    }


}
