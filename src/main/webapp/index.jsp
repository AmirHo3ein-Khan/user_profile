<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
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
        .custom-file-input {
            opacity: 0; /* Hide the default file input */
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
            content: " ✔"; /* Add a checkmark */
            color: #52a883; /* Green checkmark */
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h3 class="h3-color">Sign Up</h3>
                </div>
                <div class="card-body">
                    <form action="signup" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" name="username" class="form-control input-color" id="username"
                                   aria-describedby="usernameHelp" placeholder="Enter username...">
                            <small id="usernameHelp" class="form-text text-muted">Please remember your username.</small>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" class="form-control input-color" id="password"
                                   placeholder="Enter password...">
                            <small id="passwordHelp" class="form-text text-muted">Please remember your password.</small>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" class="form-control input-color" id="email"
                                   placeholder="Enter email...">
                        </div>
                        <div class="form-group">
                            <label for="fullName">Full Name</label>
                            <input type="text" name="fullName" class="form-control input-color" id="fullName"
                                   placeholder="Enter your full name...">
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber">Phone Number</label>
                            <input type="text" name="phoneNumber" class="form-control input-color" id="phoneNumber"
                                   placeholder="Enter your phone number...">
                        </div>

                        <div class="form-group">
                            <label for="profileImage">Profile image</label>
                            <div class="custom-file">
                                <input type="file" name="profileImage" id="profileImage" accept="image/*" class="custom-file-input" required>
                                <label class="custom-file-label" for="profileImage">
                                    📁 Choose a file...
                                </label>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">Sign Up</button>
                        <div class="container">
                            <small style="color: #d32727"><%=(request.getAttribute("message") != null ?  request.getAttribute("message") : "") %>
                            </small>
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <a href="login" style="color: #52a883">Already have an account? Login</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>