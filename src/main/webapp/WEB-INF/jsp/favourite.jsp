<%--
  Created by IntelliJ IDEA.
  User: ann
  Date: 08.02.2025
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Избранные фильмы | FileCatalog</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"> <!-- Подключаем внешний CSS -->
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }
    .container {
      width: 80%;
      margin: 20px auto;
      background: white;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      text-align: center;
    }
    .film-list {
      list-style: none;
      padding: 0;
    }
    .film-item {
      padding: 15px;
      border-bottom: 1px solid #ddd;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .film-title h3 {
      margin: 0;
    }
    .film-actions a {
      padding: 8px 12px;
      margin-left: 10px;
      background: #333;
      color: white;
      text-decoration: none;
      border-radius: 5px;
    }
    .film-actions a:hover {
      background: #555;
    }
    .back-button {
      display: block;
      margin-top: 20px;
      text-align: center;
      padding: 10px;
      background: #333;
      color: white;
      text-decoration: none;
      border-radius: 5px;
    }
    .back-button:hover {
      background: #555;
    }
    footer {
      text-align: center;
      padding: 10px;
      background: #333;
      color: white;
      margin-top: 20px;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Избранные фильмы</h2>
  <c:choose>
    <c:when test="${empty favoriteFilms}">
      <p>У вас пока нет избранных фильмов.</p>
    </c:when>
    <c:otherwise>
      <ul class="film-list">
        <c:forEach var="film" items="${favoriteFilms}">
          <li class="film-item">
            <div class="film-title">
              <h3>${film.title}</h3> <!-- Отображаем название фильма -->
              <p>Автор: ${film.author}</p> <!-- Отображаем автора -->
            </div>
          </li>
        </c:forEach>
      </ul>
    </c:otherwise>
  </c:choose>
  <a class="back-button" href="${pageContext.servletContext.contextPath}/">Назад на главную</a>
</div>
<footer>
  <p>&copy; 2025 FilmCatalog. Все права защищены.</p>
</footer>
</body>
</html>
