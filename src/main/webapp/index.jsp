<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #d4eafc;
            margin: 30px;
        }

        h1 {
            display: flex;
            justify-content: center;
            color: #2c3e50;
        }

        a {

            justify-content: center;
            align-items: center;
            width: 150px;
            display: flex;
            margin: 10px 0;
            padding: 10px 15px;
            text-decoration: none;
            background-color: #3498db;
            color: #fff;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #2980b9;
        }

        br {
            line-height: 1.5em;
        }
        .main-div{
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 50px;
        }
    </style>
</head>
<body>
<h1><%= "Cinema App" %>
</h1>
<br/>
<div class="main-div">
    <a href="users">Registration</a><br>
    <a href="login.html">Login</a><br>

</div>





</body>
</html>
