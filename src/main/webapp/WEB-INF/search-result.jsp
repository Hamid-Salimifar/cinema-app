<%@ page import="ir.maktabsharif.model.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sina
  Date: 11/1/2025
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Searching Movie</title>
</head>

<style>
    .text-in-movieBox {
        margin-left: 6px;
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

    .movie-box-button .details-btn {
        background-color: #007c7d;
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

</style>
<body>

<h1>Search</h1>
<div class="movies-container">
    <c:forEach var="movie" items="${searchResults}">
        <div class="movie-box">
            <div class="image-container">
                <c:choose>
                    <c:when test="${not empty movie.posterImage}">
                        <img src="${movie.posterImage}" alt="Poster">
                    </c:when>
                    <c:otherwise>
                        <img src="defaultMovieImage.jpg" alt="default img">
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="text-in-movieBox">
                <h3>${movie.title}
                </h3>
                <p>${movie.genre}
                </p>
                <p> 🗓️${movie.releaseDate}
                </p>
                <p>⭐ Rating: ${movie.overAllRating}
                </p>
            </div>

            <div class="movie-box-button">
                <form action="${pageContext.request.contextPath}/watchlist" method="post">
                    <input type="hidden" id="movieId" name="movieId" value="${movie.id}">
                    <button type="submit" class="add-btn">💚 Add to watch list</button>
                </form>

                <form action="${pageContext.request.contextPath}/movie-details" method="get">
                    <input type="hidden" id="movieId" name="movieId" value="${movie.id}">
                    <button type="submit" class="details-btn">Details</button>
                </form>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
