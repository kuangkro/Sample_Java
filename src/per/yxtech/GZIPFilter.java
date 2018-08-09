package per.yxtech;

import com.helper.GZIPResponseWarpper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GZIPFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        if(req instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest)req;
            HttpServletResponse response = (HttpServletResponse)rep;
            //依据浏览器的Header信息，判断支持的编码格式
            String ae =request.getHeader("Accept-Encoding");
            //如果浏览器支持GZIP格式，则使用GZIP进行压缩
            if(ae != null && ae.toLowerCase().indexOf("gzip") != -1){
                GZIPResponseWarpper warpper = new GZIPResponseWarpper(response);
                filterChain.doFilter(req, warpper);
                warpper.finishResponse();
                return;
            }
            filterChain.doFilter(req, rep);
        }
    }

    @Override
    public void destroy() {

    }
}