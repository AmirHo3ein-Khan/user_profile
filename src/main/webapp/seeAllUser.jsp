<%@ page import="ir.maktabsharif.userprofile.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 1/21/2025
  Time: 9:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>See All Users</title>
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
    List<User> users = (List<User>) request.getSession().getAttribute("users");
    if (!users.isEmpty()) {
        for (User user : users) {
%>
<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header text-center">
                    <h3 class="mb-0">Profile</h3>
                </div>
                <div class="card-body text-center">

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
                </div>
            </div>
        </div>
    </div>
</div>
<%
        }
    }
%>

<div class="footer text-center mt-5 mb-5">
    <a href="profile" style="color: #52a883" type="submit">Get back to profile page.</a>
</div>
</body>
</html>
