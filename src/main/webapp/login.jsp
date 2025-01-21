<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 1/11/2025
  Time: 6:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .btn-primary {
            cursor: pointer;
            color: #ffffff;
            background-color: #52a883;
            border: none;
        }

        .btn-primary:hover {
            background-color: #218838;
        }

        .input-color {
            background-color: #f8f9fa;
        }
        .h3-color{
            color: #52a883;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h3 class="h3-color">Login</h3>
                </div>
                <div class="card-body">
                    <form action="login" method="post">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" name="username" class="form-control input-color" id="username"
                                   aria-describedby="usernameHelp" placeholder="Enter username...">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" class="form-control input-color" id="password"
                                   placeholder="Enter password...">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Login</button>
                        <small style="color: #d32727"><%=(request.getAttribute("error") != null ? request.getAttribute("error")  : "") %></small>
                        <%
                            String message = (String) session.getAttribute("massage");
                            if (message != null) { %>
                        <small style="color: #0da1de"><%=session.getAttribute("massage")%></small>
                        <%session.removeAttribute("massage");
                        }%>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <a href="index.jsp" style="color: #52a883">Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
