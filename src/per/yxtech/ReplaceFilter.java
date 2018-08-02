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

@WebFilter(description = "内容过滤", filterName = "replace_filter", urlPatterns = {"/*"}, initParams = { @WebInitParam(name = "filePath", value="replace_ZH.properties")})
public class ReplaceFilter implements Filter {

    private Properties propert = new Properties();

    public void init(FilterConfig config) throws ServletException{
        String filePath = config.getInitParameter("filePath");
        try{
            propert.load(ReplaceFilter.class.getClassLoader()
                    .getResourceAsStream(filePath));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse rep = (HttpServletResponse)servletResponse;
        ResponseReplaceWrapper resp = new ResponseReplaceWrapper(rep);
        filterChain.doFilter(servletRequest, resp);

        String outString = resp.getCharWriter().toString();
        for(Object o:propert.keySet()){
            String key = (String)o;
            outString = outString.replace(key, propert.getProperty(key));
        }

        PrintWriter writer = resp.getWriter();
        writer.write(outString);
    }

    @Override
    public void destroy() {

    }


}
