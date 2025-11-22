<%@ page import="ir.maktabsharif.model.Users" %>
<%@ page import="ir.maktabsharif.model.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sina
  Date: 10/19/2025
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Library</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        #role-show {
            background-color: #b6d4fe;
            padding: 12px;
            color: darkblue;
            border-radius: 5px;
            border: solid 1px skyblue;
        }

        .top-bar {
            background-color: silver;
            display: flex;

            padding: 10px 20px;
            align-items: center;
            justify-content: space-between;
            border-radius: 5px;
        }

        .top-buttons button {
            padding: 9px;
            background-color: grey;
            border-radius: 5px;
            color: white;
            margin-left: 15px;
            margin-right: 15px;
            cursor: pointer;
            border: 1px solid silver;
        }

        .top-buttons button:hover {
            border: 1px solid black;
            transition: 0.3s;
        }

        .search-box input {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .search-box button {
            padding: 8px 12px;
            margin-left: 5px;
            background-color: grey;
            color: white;
            border: 1px solid silver;
            border-radius: 5px;
            cursor: pointer;
        }

        .search-box button:hover {
            filter: brightness(0.8);
            border: 1px solid white;
            transition: 0.3s;

        }
        .search-box form {
            display: flex;
            align-items: center;
        }
        .search-box input,
        .search-box button {
            line-height: normal;
            vertical-align: middle;
            margin: 0;
        }



        .movies-container {
            background-color: white;
            display: flex;
            margin-top: 10px;
            flex-wrap: wrap;
            justify-content: center;
            gap: 15px;
            padding: 5px;
            align-items: stretch;
        }

        .movie-box {

            display: flex;
            flex-direction: column;
            width: 30%;
            border: 1px solid lightgray;
            border-radius: 5px;
            min-height: 250px;
        }

        .image-container {
            width: 100%;
            height: 200px;
            overflow: hidden;
            border-bottom: solid 1px lightgray;

        }

        .image-container img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            display: block;
        }

        .movie-box-button {
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: lightgray;
            border: 1px solid lightgray;
            gap: 5px;
            padding: 6px;
        }

        .movie-box-button button {

            border-radius: 5px;
            padding: 8px 12px;
            cursor: pointer;
            display: block;
            line-height: 1;
        }

        .movie-box-button button:hover {
            filter: brightness(0.8);
        }

        .movie-box-button .edit-btn {
            background-color: orange;
            color: black;
            border: none;

        }

        .movie-box-button .details-btn {
            background-color: #007c7d;
            color: white;
            border: none;

        }

        .movie-box-button .delete-btn {
            background-color: red;
            color: white;
            border: none;

        }

        .movie-box-button .add-btn {
            background-color: lightgray;
            color: green;

            border: 1px solid green;

        }

        .movie-box-button form {
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }

        .text-in-movieBox {
            margin-left: 6px;
        }

        .navigation-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;

        }

        .navigation-bar button {
            background-color: white;
            border: 1px solid white;
            padding: 0;
        }

        /*.navigation-bar button:hover{*/
        /*    */
        /*    border: 1px solid silver;*/
        /*}*/
        .navigation-bar .logout-icon {
            background-color: white;
            width: 50px;
            height: 50px;
            border-radius: 10px;
        }

        .navigation-bar .logout-icon:hover {
            background-color: silver;
            cursor: pointer;

        }

        .top-buttons a.btn-link {
            display: inline-block;
            padding: 9px 15px;
            background-color: grey;
            border-radius: 5px;
            color: white;
            text-decoration: none;
            margin: 0 10px;
            border: 1px solid silver;
            cursor: pointer;
        }

        .top-buttons a.btn-link:hover {
            border: 1px solid white;
            filter: brightness(0.8);
            transition: 0.3s;
        }


    </style>
</head>
<body>
<%
    Users user = (Users) session.getAttribute("user");
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
%>
<div class="navigation-bar">
    <h1>🎬 Movie Library</h1>
    <form action="<%=request.getContextPath()%>/logout" method="post">
        <button type="submit" class="logout">
            <img class="logout-icon" src="icons8-logout-64.png" alt="logout">
        </button>
    </form>
</div>

<p id="role-show">logged in as <strong><%=user.getUserName()%>
</strong> (Role:<%= user.getRole().toString()%>)</p>
<div class="top-bar">
    <div class="top-buttons">
        <% if (user != null && "ADMIN".equalsIgnoreCase(user.getRole().toString())) {%>
        <a href="<%=request.getContextPath()%>/add-movies" class="btn-link">add Movies</a>
        <% }%>

        <a href="<%=request.getContextPath()%>/edit-information.jsp" class="btn-link">Edit Information</a>

        <a href="<%=request.getContextPath()%>/watchlist" class="btn-link">Watch List</a>
    </div>

    <div class="search-box">
        <form action="<%=request.getContextPath()%>/search" method="get">
            <input type="text" name="movie-name" id="movie-name" placeholder="Search Movies...">
            <button type="submit">search</button>
        </form>
    </div>
</div>


<div class="movies-container">
    <%
        if (movies != null && !movies.isEmpty()) {
            for (Movie movie : movies) {
    %>

    <div class="movie-box">
        <div class="image-container">
            <%if (movie.getPosterImage() != null) {%>
            <img src="<%=movie.getPosterImage()%>" alt="Poster">
            <%} else { %>
            <img src="defaultMovieImage.jpg" alt="default img">
            <%}%>
        </div>

        <div class="text-in-movieBox">
            <h3><%=movie.getTitle()%>
            </h3>
            <p style="color: #6c757d"><%=movie.getGenre()%>
            </p>
            <p>🗓️ <%=movie.getReleaseDate()%>
            </p>
            <p>⭐ Rating: <%=movie.getOverAllRating()%>
            </p>
            <p><%=movie.getDescription()%>
            </p>
        </div>


        <div class="movie-box-button">
            <form action="<%=request.getContextPath()%>/watchlist" method="post">
                <input type="hidden" id="movieId" name="movieId" value="<%=movie.getId()%>">
                <button type="submit" class="add-btn">💚 Add to watch list</button>
            </form>

            <form action="<%=request.getContextPath()%>/movie-details" method="get">
                <input type="hidden" id="movieId" name="movieId" value="<%=movie.getId()%>">
                <button type="submit" class="details-btn">Details</button>
            </form>
            <%if (user != null && "ADMIN".equalsIgnoreCase(user.getRole().toString())) {%>
            <form action="<%= request.getContextPath() %>/edit-movie" method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= movie.getId() %>">
                <button type="submit" class="edit-btn">Edit</button>
            </form>

            <form action="<%= request.getContextPath() %>/delete-movie" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= movie.getId() %>">
                <button type="submit" class="delete-btn">Delete</button>
            </form>
            <%}%>
        </div>


    </div>
    <% }
    } else {%>
    <p>no movies found</p>
    <%}%>

</div>
</body>
</html>
