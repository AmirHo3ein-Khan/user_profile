<%@ page import="ir.maktabsharif.userprofile.service.UserService" %>
<%@ page import="ir.maktabsharif.userprofile.service.UserServiceImpl" %>
<%@ page import="ir.maktabsharif.userprofile.model.dto.LoginRequestDto" %>
<%@ page import="ir.maktabsharif.userprofile.model.dto.LoginResponseDto" %>
<%@ page import="java.util.Optional" %>
<%@ page import="ir.maktabsharif.userprofile.model.dto.LoggedInDto" %>
<%@ page import="ir.maktabsharif.userprofile.model.Permission" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .profile-image {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
            border: 3px solid #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card-header {
            background-color: #52a883;
            color: white;
            border-radius: 15px 15px 0 0;
        }

        .btn-edit {
            background-color: #52a883;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .btn-edit:hover {
            background-color: #218838;
        }

        .info-item {
            margin-bottom: 15px;
            font-size: 1.1em;
        }

        .info-item strong {
            color: #85c0b1;
        }
    </style>
</head>
<body>
<%
    LoggedInDto user = (LoggedInDto) request.getAttribute("user");
%>
<%if (user != null) {%>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header text-center">
                    <h3 class="mb-0">Profile</h3>
                </div>
                <div class="card-body text-center">
                    <%
                        String message = (String) session.getAttribute("massage");
                        if (message != null) { %>
                    <p style="color: #0da1de"><%=session.getAttribute("massage")%>
                    </p>
                    <%
                            session.removeAttribute("massage");
                        }
                    %>

                    <div class="mb-4">
                        <img src="data:image/jpeg;base64,<%= user.getProfileImage() %>" alt="Profile Image"
                             class="profile-image">
                    </div>

                    <div class="text-start">
                        <div class="info-item">
                            <strong>Username:</strong> <%= user.getUsername() %>
                        </div>
                        <div class="info-item">
                            <strong>Full Name:</strong> <%= user.getFullName() %>
                        </div>
                        <div class="info-item">
                            <strong>Email:</strong> <%= user.getEmail() %>
                        </div>
                        <div class="info-item">
                            <strong>Phone:</strong> <%= user.getPhoneNumber() %>
                        </div>
                    </div>
                    <div class="text-center">
                        <a href="editProfile.jsp" class="btn btn-edit">Edit Profile</a>
                    </div>
                </div>
                <div class="card-footer text-center">

                    <form action="logout" method="post">
                        <button type="submit" class="btn btn-primary btn-block btn-edit">Logout</button>
                    </form>
                    <div class="card-footer text-center">
                        <a href="changePass.jsp" style="color: #52a883" type="submit" >Change Password</a>
                    </div>

                    <% for (Permission permission : user.getRole().getPermissions()){
                    if (permission.getPermission().equals("SEE_ALL_USERS")) {%>
                    <div class="card-footer text-center">
                        <a href="users" style="color: #52a883" type="submit" >See all users</a>
                    </div>
                    <%}
                    }%>
                </div>

            </div>
        </div>
    </div>
</div>
<%}%>
</body>
</html>
