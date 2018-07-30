<%--
  Created by IntelliJ IDEA.
  User: Tony
  Date: 2018/7/4
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/fileupload" method="post" enctype="multipart/form-data">
        <label>选择文件：</label>
        <input type="file" name="filename" />
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
