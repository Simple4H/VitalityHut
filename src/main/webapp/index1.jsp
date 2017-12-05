<%--
  Created by IntelliJ IDEA.
  User: S I M P L E
  Date: 2017/12/5 0005
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
springmvc上传文件
<form name="form1" action="/user/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="springmvc上传文件"/>
</form>

</body>
</html>
