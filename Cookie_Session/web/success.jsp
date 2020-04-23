<%--
  Created by IntelliJ IDEA.
  User: zhaod
  Date: 2020/4/20
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        span {
            font-family: SimSun-ExtB, serif;
            color: red;
            font-size: xx-large;
        }
    </style>
</head>
<body>

<h1>恭喜您,
    <span>
        <%=request.getSession().getAttribute("user")%>
    </span>
    ！登录成功...
</h1>
</body>
</html>
