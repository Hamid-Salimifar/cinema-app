<%@ page import="ir.maktabsharif.model.Users" %>
<%@ page import="ir.maktabsharif.model.Movie" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Watchlist</title>
    <style>
        .text-in-movieBox{
            margin-left: 6px;
        }
        .movies-container{
            background-color: white;
            display: flex;
            margin-top: 10px;
            flex-wrap: wrap;
            justify-content: center;
            gap: 15px;
            padding: 5px;
            align-items: stretch;
        }
        .movie-box{

            display: flex;
            flex-direction: column;
            width: 30%;
            border: 1px solid lightgray;
            border-radius: 5px;
            min-height: 250px;
        }
        .image-container{
            width: 100%;
            height: 200px;
            overflow: hidden;
            border-bottom: solid 1px lightgray;

        }

        .image-container img{
            width: 100%;
            height: 100%;
            object-fit: cover;
            display: block;
        }

    </style>
</head>
<body>
<%
    Users user = (Users) session.getAttribute("user");
    Set<Movie> movies = user.getMovies();
%>


<h1>💚 My Watchlist</h1>
<div class="movies-container">

    <% if (movies != null && !movies.isEmpty()) {
        for (Movie movie : movies) { %>
    <div class="movie-box">
        <div class="image-container">
            <% if (movie.getPosterImage() != null) { %>
            <img src="<%=movie.getPosterImage()%>" alt="Poster">
            <% } else { %>
            <img src="defaultMovieImage.jpg" alt="default img">
            <% } %>
        </div>




        <div class="text-in-movieBox">
            <h3><%=movie.getTitle()%></h3>
            <p><%=movie.getGenre()%></p>
            <p> 🗓️<%=movie.getReleaseDate()%></p>
            <p>⭐ Rating: <%=movie.getOverAllRating()%></p>

        </div>

    </div>
    <% } } else { %>
    <p>No movies found</p>
    <% } %>
</div>
</body>
</html>
