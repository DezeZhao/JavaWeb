<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>


    <script>
        window.onload = function () {
            document.getElementById("img").onclick = function () {
                this.src = "/Cookie_Session_war_exploded/checkCodeServlet?time=" + new Date().getTime();
            }

            //document.getElementById("xxx").innerHTML=${name1};

        }

    </script>
    <style>
        div {
            color: red;
        }

    </style>
</head>
<body>
<%
    session.setAttribute("name1","李四");
%>
<form action="/Cookie_Session_war_exploded/loginServlet" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>

        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>

        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="checkCode"></td>

        </tr>
        <tr>
            <td colspan="2"><img id="img" src="/Cookie_Session_war_exploded/checkCodeServlet"></td>
        </tr>
        <tr>
            <td colspan="2"><p id="xxx"></p></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"></td>
        </tr>
    </table>
</form>
<div>
    <%=request.getAttribute("login_error") == null ? "" : request.getAttribute("login_error") %>
</div>
<div>
    <%=request.getAttribute("cc_error") == null ? "" : request.getAttribute("cc_error")%>
</div>
</body>
</html>