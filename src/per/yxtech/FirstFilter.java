package per.yxtech;

import com.helper.MyEncodingWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        description = "first filter",
        filterName = "myfilter",
        servletNames = { "*.do" },
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name="ENCODING", value="UTF-8")
        },
        dispatcherTypes = { DispatcherType.REQUEST }
)
public class FirstFilter implements Filter{
    private String encoding="";
    private String filterName="";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("ENCODING");
        filterName = filterConfig.getFilterName();

        if(filterName == "" || "".equals(encoding) || null == encoding){
            encoding = "UTF-8";
        }

        System.out.println("获得编码值");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        System.out.println("请求被"+filterName+"过滤");

        if("GET".equals(req.getMethod())){
            req = new MyEncodingWrapper(req, encoding);
        }else{
            servletRequest.setCharacterEncoding(encoding);
        }

        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(req, servletResponse);
        System.out.println("响应被"+filterName+"过滤");
    }

    @Override
    public void destroy() {
        System.out.println("请求销毁");
    }
}
