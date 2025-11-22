<%--
  Created by IntelliJ IDEA.
  User: sina
  Date: 10/20/2025
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add movie</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
    }

    .add-movie-container {
        background-color: white;
        margin: 10px 10%;
        padding: 20px;
    }

    .add-movie-container h2 {
        font-size: x-large;
        cursor: default;
    }

    .add-movie-container input[type=text] {
        border-radius: 5px;
        width: 100%;

        border: 1px solid #cfcfcf;
        padding: 8px;
        margin-bottom: 10px;

        outline: none;
    }

    .add-movie-container input[type=file] {
        border-radius: 5px;
        width: 100%;

        border: 1px solid #cfcfcf;
        padding: 8px;
        margin-bottom: 10px;

        outline: none;
    }

    .add-movie-container input[type=submit] {
        background-color: rgb(30, 105, 255);
        margin-top: 10px;
        color: white;
        border-radius: 6px;
        padding: 3px 3px;
        width: 100px;
        height: 40px;
        border: none;
        cursor: pointer;
        font-size: larger;
    }

    .add-movie-container input[type=submit]:hover {
        background-color: #0a53be;
        transition: 0.5s;
    }

    .add-movie-container label {
        margin-bottom: 6px;
        color: #585858;
    }

    #poster-preview {
        display: none;
        width: 125px;
        border-radius: 5px;

    }
</style>
<body>

<div class="add-movie-container">
    <h2>Add movie</h2>


    <form action="<%=request.getContextPath()%>/add-movies" method="post" enctype="multipart/form-data">
        <label for="title">title</label><br>
        <input type="text" id="title" name="title"><br>

        <label for="description">Description</label><br>
        <input type="text" id="description" name="description"><br>

        <label for="releaseDate">ReleaseDate</label><br>
        <input type="text" name="releaseDate" id="releaseDate"><br>

        <label for="genre">genre</label><br>
        <input type="text" id="genre" name="genre"><br>

        <label for="duration">duration</label><br>
        <input type="text" name="duration" id="duration"><br>

        <label for="rating">rating</label><br>
        <input type="text" name="rating" id="rating"><br>

        <label for="poster"></label><br>
        <input type="file" name="poster" id="poster"><br>

        <img src="" id="poster-preview">

        <input type="submit" VALUE="Submit">
    </form>

</div>

<script>
    const posterImage = document.getElementById('poster');
    const posterPreview = document.getElementById("poster-preview");

    posterImage.addEventListener('change', function (event) {
        const file = event.target.files[0];
        if(file){
            const reader=new FileReader();
            reader.onload=function (e){
                posterPreview.src=e.target.result;
                posterPreview.style.display="block";

            }
            reader.readAsDataURL(file);

        }else {
            posterPreview.style.display='none';
            posterPreview.src='';
        }
    });

</script>
</body>
</html>
