<%--
  Created by IntelliJ IDEA.
  User: sina
  Date: 10/21/2025
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>forgot-password-validation</title>
</head>
<style>
    body{
        font-family: Arial,sans-serif;
    }
    .main-div {
        background-color: white;
        margin: 10px 10%;
        padding: 20px;

    }
    .main-div h2{
        font-size: x-large;
        cursor: default;
    }
    .main-div input[type=text]{
        border-radius: 5px;
        width: 100%;

        border: 1px solid #cfcfcf;
        padding: 8px;
        margin-bottom: 10px;

        outline: none;
    }
    .main-div input[type=submit]{
        background-color: rgb(30, 105, 255);
        margin-top: 10px;
        color: white;
        border-radius: 6px;
        padding: 3px 3px;
        width: 80px;  height: 40px;
        border: none;
        cursor: pointer;
        font-size: larger;
    }
</style>
<body>
<div class="main-div">
    <h2>enter your username and email:</h2>
    <form action="forgot-password" method="post">
        <label for="username">username:</label><br>
        <input type="text" name="username" id="username"><br>

        <label for="email">email:</label><br>
        <input type="text" name="email" id="email"><br>


        <input type="submit" value="submit"><br>


    </form>
</div>

</body>
</html>
