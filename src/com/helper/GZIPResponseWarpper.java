package com.helper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class GZIPResponseWarpper extends HttpServletResponseWrapper {

    //原始的response
    private HttpServletResponse response = null;
    //自定义的outstream，对数据压缩，并输出
    private ServletOutputStream outputStream = null;
    //自定义printwriter，将内容输出到servletoutstream
    private PrintWriter writer = null;

    public GZIPResponseWarpper(HttpServletResponse response) {
        super(response);

        this.response = response;
    }

    public ServletOutputStream createOutputStream() throws IOException {
        return new GZIPResponseStream(this.response);
    }

    //执行这个方法时，对数据进行GZIP压缩，并输出到浏览器中
    public void finishResponse() {
        try {
            if (writer != null) {
                writer.close();
            } else {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }catch (IOException ex){}
    }

    public void flushBuffer() throws IOException{
        outputStream.flush();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if(writer != null){
            throw new IllegalStateException("getWriter() has been called");
        }

        if(outputStream == null) outputStream = createOutputStream();
        return outputStream;
    }

    public PrintWriter getWriter() throws IOException{
        if(writer != null){
            return writer;
        }

        if(outputStream != null){
            throw new IllegalStateException("getOutputStream() has already been called");
        }

        outputStream = createOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));

        return writer;
    }
}
