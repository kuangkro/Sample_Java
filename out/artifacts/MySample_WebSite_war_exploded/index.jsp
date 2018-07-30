<%--
  Created by IntelliJ IDEA.
  User: Tony
  Date: 2018/7/2
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
  <head>
    <title>我的第一个网页</title>
  </head>
  <body>
      <%@include file="partial page/_top_page.jsp"%>
      <%!
          int x,y=60, z;
          String name="张晓佳";
          Date dt = new Date();
      %>

    <%!
        int add(int m, int n){
            return m+n;
        }
    %>

    <%!
        int multuple(int m, int n){
            return m * n;
        }
    %>

    <%
        out.println("我的名字："+name);
        out.println("1+2="+add(1,2));
        out.println("1*2="+multuple(1,2));
    %>

    当前时间：<%=new Date()%>
    <div align="center">JSP include指令演示</div>
    <%@include file="partial page/_bottom_page.jsp"%>
    <jsp:forward page="share/page1.jsp">
        <jsp:param name="name" value="张晓佳" />
        <jsp:param name="name" value="张倩" />
        <jsp:param name="name" value="李志成" />
        <jsp:param name="password" value="123654" />
        <jsp:param name="address" value="沙洲县" />
    </jsp:forward>
  </body>
</html>
