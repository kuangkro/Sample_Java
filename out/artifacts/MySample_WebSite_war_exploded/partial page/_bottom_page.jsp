<%--
  Created by IntelliJ IDEA.
  User: Tony
  Date: 2018/7/3
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,java.text.SimpleDateFormat" %>
<%
    Date d= new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String t = sdf.format(d);
    String copyright =  "张晓佳 版权所有 2010-"+t;
%>
<div align="center"><%=copyright%></div>