<%--
  Created by IntelliJ IDEA.
  User: Tony
  Date: 2018/7/3
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<html>
<head>
    <title>page_1</title>
    <%
        String userName = request.getParameter("name");
        String pwd = request.getParameter("password");
        String address = request.getParameter("address");
        Enumeration<String> values = request.getParameterNames();
        String[] vals =  request.getParameterValues("name");
    %>
</head>
<body>
    <%
        while(values.hasMoreElements()) {
            System.out.println( values.nextElement());
        }
    %>
    <% System.out.println("========================================"); %>
    <%
        for(int i=0; i<vals.length; i++) {
            System.out.println(i+ ":"+ vals[i]);
        }
    %>
    <center>
        <table>
            <tr>
                <th>子页面：人员信息</th>
            </tr>
        </table>
    </center>
    <center>
        <table>
            <tr>
                <td>用户名：<%=userName%></td>
            </tr>
            <tr>
                <td>密码：<%=pwd%></td>
            </tr>
            <tr>
                <td>地址：<%=address%></td>
            </tr>
        </table>
    </center>
</body>
</html>
