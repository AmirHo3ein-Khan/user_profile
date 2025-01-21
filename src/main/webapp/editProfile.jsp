<%@ page import="ir.maktabsharif.userprofile.model.dto.LoggedInDto" %>
<%@ page import="ir.maktabsharif.userprofile.service.UserService" %>
<%@ page import="ir.maktabsharif.userprofile.service.UserServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 1/18/2025
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Profile</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <title>Signup</title>
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
    .profile-image {
      width: 150px;
      height: 150px;
      object-fit: cover;
      border-radius: 50%;
      border: 3px solid #fff;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .custom-file-input {
      opacity: 0;
      position: absolute;
      z-index: -1;
    }

    .custom-file-label {
      display: inline-block;
      padding: 10px 15px;
      background-color: #f8f9fa;
      border: 1px solid #ced4da;
      border-radius: 4px;
      cursor: pointer;
      font-size: 14px;
      color: #495057;
      transition: background-color 0.3s ease;
    }

    .custom-file-label:hover {
      background-color: #85c0b1;
    }
    .custom-file-input:valid + .custom-file-label::after {
      content: " ✔";
      color: #52a883;
    }
  </style>
</head>
<body>
<%
  String username = (String) request.getSession().getAttribute("username");
  UserService userService = new UserServiceImpl();
  LoggedInDto user = userService.loggedInUserByUsername(username);
%>

<%if (user!= null){%>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header text-center">
          <h3 class="h3-color">Edit Profile</h3>
        </div>
        <div class="card-body">
          <form action="updateProfile" method="post" enctype="multipart/form-data">

            <div class="form-group">
              <img src="data:image/jpeg;base64,<%= user.getProfileImage() %>" alt="Profile Image" class="profile-image">
              <label for="profileImage">Profile image</label>
              <div class="custom-file">
                <input type="file" name="profileImage" id="profileImage" accept="image/*" class="custom-file-input" required>
                <label class="custom-file-label" for="profileImage">
                  📁 Change profile...
                </label>
              </div>
            </div>

            <div class="form-group">
              <label for="username">Username</label>
              <input type="text" name="username" class="form-control input-color" id="username"
                     aria-describedby="usernameHelp" placeholder="Enter username..." value="<%=user.getUsername()%>">
              <small id="usernameHelp" class="form-text text-muted">Please remember your username.</small>
            </div>

            <div class="form-group">
              <label for="email">Email</label>
              <input type="email" name="email" class="form-control input-color" id="email"
                     placeholder="Enter email..." value="<%=user.getEmail()%>">
            </div>

            <div class="form-group">
              <label for="fullName">Full Name</label>
              <input type="text" name="fullName" class="form-control input-color" id="fullName"
                     placeholder="Enter your full name..." value="<%=user.getFullName()%>">
            </div>

            <div class="form-group">
              <label for="phoneNumber">Phone Number</label>
              <input type="text" name="phoneNumber" class="form-control input-color" id="phoneNumber"
                     placeholder="Enter your phone number..." value="<%=user.getPhoneNumber()%>">
            </div>


            <button type="submit" class="btn btn-primary btn-block">Save Changes</button>

          </form>
          <div class="card-footer text-center">
            <a href="profile" style="color: #52a883">Get back to profile page.</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%}%>
</body>
</html>
