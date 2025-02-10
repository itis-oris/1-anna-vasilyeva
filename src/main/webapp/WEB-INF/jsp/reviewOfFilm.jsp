<%--
  Created by IntelliJ IDEA.
  User: ann
  Date: 07.02.2025
  Time: 13:57
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
    <title>Отзывы | ${film.getTitle()}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"> <!-- подключаем внешний CSS -->
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
        }
        h2 {
            text-align: center;
        }
        .review-list {
            list-style: none;
            padding: 0;
        }
        .review-item {
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        .reviewer-name {
            font-weight: bold;
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
    <h2>Отзывы на фильм: ${film.getTitle()}</h2>
    <ul class="review-list">
        <c:forEach var="review" items="${reviews}">
            <li class="review-item">
                <p class="reviewer-name">${review.getReviewerName()}</p>
                <p>${review.getReviewText()}</p>
            </li>
        </c:forEach>
    </ul>
    <a class="back-button" href="${pageContext.servletContext.contextPath}/all">Назад ко всем фильмам</a>
</div>

<footer>
    <p>&copy; 2025 FilmCatalog. Все права защищены.</p>
</footer>
</body>
</html>

