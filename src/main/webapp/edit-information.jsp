<%@ page import="ir.maktabsharif.model.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
        }

        .edit-container {
            background-color: white;
            margin: 30px 10%;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .edit-container h2 {
            font-size: x-large;
            cursor: default;
            margin-bottom: 20px;
        }

        .edit-container img {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            background-color: #ccc;
            display: block;
            margin: 0 auto 15px auto;
        }

        .edit-container form {
            text-align: left;
            width: 100%;
            max-width: 500px;
            margin: 0 auto;
        }

        .edit-container label {
            display: block;
            margin-bottom: 6px;
            color: #585858;
            font-weight: normal;
        }

        .edit-container input[type="text"],
        .edit-container input[type="file"] {
            border-radius: 5px;
            width: 100%;
            border: 1px solid #cfcfcf;
            padding: 8px;
            margin-bottom: 10px;
            outline: none;
        }

        .edit-container input[type="submit"] {
            background-color: rgb(30, 105, 255);
            color: white;
            border-radius: 6px;
            padding: 8px 12px;
            width: 25%;
            border: none;
            cursor: pointer;
            font-size: medium;
        }

        .edit-container input[type="submit"]:hover {
            background-color: #0a53be;
            transition: 0.4s;
        }

        .edit-container a button {
            margin-top: 10px;
            background-color: #e0e0e0;
            border: none;
            border-radius: 6px;
            padding: 8px 12px;
            cursor: pointer;
        }

        .edit-container a button:hover {
            background-color: #ccc;
        }

    </style>
</head>
<body>
<%
    Users user = (Users) session.getAttribute("user");
    String profileImg = (user != null && user.getProfileImage() != null)
            ? user.getProfileImage()
            : request.getContextPath() + "/icons8-person-100.png";
%>

<div class="edit-container">
    <img src="<%= profileImg %>" alt="Profile Image">
    <h2>Edit Profile</h2>

    <form action="editInformation" method="post" enctype="multipart/form-data">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username"
               value="<%= session.getAttribute("username") %>">

        <label for="email">Email:</label>
        <input type="text" name="email" id="email"
               value="<%= ((Users) session.getAttribute("user")).getEmail() %>">

        <label for="image">Choose Profile Picture:</label>
        <input type="file" name="image" id="image">

        <input type="submit" value="Save">
    </form>

    <a href="movie-library">
        <button>Go back to movie library</button>
    </a>
</div>

</body>
</html>
