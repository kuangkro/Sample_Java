package com.helper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZIPResponseStream extends ServletOutputStream {

    protected ByteArrayOutputStream bArrayOutputStream = null;
    protected GZIPOutputStream gzipOutputStream = null;
    protected boolean closed = false;
    protected HttpServletResponse response=null;
    protected ServletOutputStream outputStream=null;

    public GZIPResponseStream(HttpServletResponse response) throws IOException {
        super();
        this.response = response;
        this.closed=false;
        this.outputStream = response.getOutputStream();
        bArrayOutputStream = new ByteArrayOutputStream();
        gzipOutputStream=new GZIPOutputStream(bArrayOutputStream);
    }

    public void close() throws IOException{
        if(closed){
            throw new IOException("This output stream has already been closed;");
        }
        //执行压缩必须执行该方法
        gzipOutputStream.finish();
        //将压缩过后的数据输出到浏览器中
        byte[] bts = bArrayOutputStream.toByteArray();
        //设置压缩算法为GZIP，浏览器会自动解压数据
        response.addHeader("Content-Length", Integer.toString(bts.length));
        response.addHeader("Content-Encoding","GZIP");
        //输出到浏览器
        outputStream.write(bts);
        outputStream.flush();
        outputStream.close();
        closed=true;
    }

    public void flush() throws IOException{
        if(closed){
            throw new IOException("不能刷新关闭的流");
        }
        gzipOutputStream.flush();
    }

    @Override
    public void write(int b) throws IOException {
        if(closed){
            throw new IOException("输出流关闭中");
        }
        gzipOutputStream.write((byte)b);
    }

    public void write(byte[] b) throws IOException{
        if(closed){
            throw new IOException("输出流关闭中");
        }
        write(b, 0, b.length);
    }

    public void write(byte[] b, int offset, int len) throws IOException{
        if(closed){
            throw new IOException("输出流关闭中");
        }
        gzipOutputStream.write(b, offset,len);
    }

    public boolean closed(){
        return closed;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
