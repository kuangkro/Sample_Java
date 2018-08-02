package com.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

public class MyEncodingWrapper extends HttpServletRequestWrapper {
    private String encoding;
    public MyEncodingWrapper(HttpServletRequest request) {
        super(request);
    }

    public MyEncodingWrapper(HttpServletRequest request, String encoding){
        super(request);
        this.encoding = encoding;
    }

    public String getParameter(String name){
        String value = this.getRequest().getParameter(name);

        try{
            if(value == null || !"".equals(value)){
                value = new String(value.trim().getBytes("ISO-8859-1"), encoding);
            }
        }catch (UnsupportedEncodingException ex){
            ex.printStackTrace();
        }

        return value;
    }
}
