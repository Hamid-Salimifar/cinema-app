<%@ page import="ir.maktabsharif.model.Movie" %>
<%@ page import="ir.maktabsharif.model.Users" %>
<%@ page import="java.util.List" %>
<%@ page import="ir.maktabsharif.model.Comment" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: sina
  Date: 10/25/2025
  Time: 5:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .main-div {
            background-color: white;
            margin: 10px 10%;
            padding: 20px;
        }

        .main-div h3 {
            font-size: large;
            cursor: default;
        }

        .main-div p {
            margin-top: 2px;
            display: flex;
            border: 1px solid silver;
            border-radius: 5px;
            color: dimgray;
            min-height: 3em;
            max-height: calc(1.4em * 5);
            overflow-y: auto;
            line-height: 1.4;
        }

        .user-comment {
            margin: 0;
            display: inline-block;
            padding: 5px;
            height: 20px;

            background-color: #d7d7cd;
            border-radius: 10px;
        }
        .add-comment{
            background-color: #f9f9f9;
            padding: 2px;
            border-radius: 10px;
            box-shadow: 1px 5px 12px rgba(0, 0, 0, 0.2);

        }
        .add-comment textarea{
            margin: 5px;
            border: none;
            border-radius: 8px;
            width: 100%;

        }
        .add-comment button{
            background-color: rgb(30, 105, 255);
            margin-top: 10px;
            color: white;
            border-radius: 8px;
            padding: 3px 3px;
            width: 70px;  height: 40px;
            border: none;
            cursor: pointer;
            font-size: larger;
        }
    /*    */

        .rate-form {
            margin-top: 10px;
        }
        .rate-form select, .rate-form button {
            padding: 5px 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .rate-form button {
            background-color: #1e69ff;
            color: white;
            border: none;
            cursor: pointer;
        }
        .rate-form button:hover {
            background-color: #1552cc;
        }

    </style>
</head>

<body>
<%
    Movie movie = (Movie) request.getAttribute("movie");
    Users user = (Users) session.getAttribute("user");
%>
<div class="main-div">


    <h3>Title</h3>
    <p><%=movie.getTitle()%>
    </p>

    <h3>Genre</h3>
    <p><%=movie.getGenre()%>
    </p>

    <h3>Description</h3>
    <p><%=movie.getDescription()%>
    </p>

    <h3>Duration</h3>
    <p><%=movie.getDuration()%>
    </p>

    <h3>Release Date</h3>
    <p><%=movie.getReleaseDate()%>
    </p>

    <h3>Rate</h3>
    <p><%=String.format("%.1f", movie.getOverAllRating())%>
    </p>
<%--    <h2>Give Rating</h2>--%>
<%--    <form action="rate-movie" method="post">--%>
<%--        <input type="hidden" name="movieId" value="<%=movie.getId()%>">--%>

<%--        <label for="user-rating">Rate this movie:</label>--%>
<%--        <select name="userRating" id="user-rating" required>--%>
<%--            <option value="">-- Select a rating --</option>--%>
<%--            <option value="1">1 ⭐</option>--%>
<%--            <option value="2">2 ⭐⭐</option>--%>
<%--            <option value="3">3 ⭐⭐⭐</option>--%>
<%--            <option value="4">4 ⭐⭐⭐⭐</option>--%>
<%--            <option value="5">5 ⭐⭐⭐⭐⭐</option>--%>
<%--        </select>--%>

<%--        <button type="submit">Submit Rating</button>--%>
<%--    </form>--%>
    <div class="rate-form">
        <h2>Give Rating</h2>
        <form action="rate-movie" method="post">
            <input type="hidden" name="movieId" value="<%=movie.getId()%>">
            <label for="user-rating">Rate this movie:</label>
            <select name="userRating" id="user-rating" required>
                <option value="">-- Select a rating --</option>
                <option value="1">1 ⭐</option>
                <option value="2">2 ⭐⭐</option>
                <option value="3">3 ⭐⭐⭐</option>
                <option value="4">4 ⭐⭐⭐⭐</option>
                <option value="5">5 ⭐⭐⭐⭐⭐</option>
            </select>
            <button type="submit">Submit</button>
        </form>
    </div>

    <h2><strong>Comment Section</strong></h2>
    <%
        Set<Comment> comments = movie.getComments();
        if (comments != null && !comments.isEmpty()) {
            for (Comment comment : comments) {%>
    <h4 class="user-comment">user: <%=comment.getAuthorName()%>
    </h4>
    <p style="background-color: #ffedc6;border: none"><%=comment.getContent()%>
    </p><br>
    <%}%>
    <%}%>
    <div class="add-comment">
        <h2>Add a new comment:</h2>
        <form action="movie-details" method="post">
            <input type="hidden" name="movieId" id="movieId" value="<%=movie.getId()%>">
            <input type="hidden" name="userId" id="userId" value="<%=user.getId()%>">

            <label for="content">Your Comment:</label><br>
            <textarea id="content" name="content" rows="5" cols="50"
                      placeholder="Write your thoughts about the movie..."
                      required></textarea><br><br>

            <button type="submit">Submit</button>
        </form>

    </div>

</div>


</body>
</html>
