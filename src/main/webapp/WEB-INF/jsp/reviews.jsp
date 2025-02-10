<%--
  Created by IntelliJ IDEA.
  User: ann
  Date: 05.02.2025
  Time: 11:43
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
  <title>FilmCatalog</title>
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
    .edit-button {
      padding: 8px 12px;
      margin-left: 10px;
      background: #333;
      color: white;
      text-decoration: none;
      border-radius: 5px;
    }
    .edit-button:hover {
      background: #555;
    }
    .edit-form {
      display: none;
      margin-top: 10px;
    }
    .edit-form textarea {
      width: 100%;
      height: 100px;
      padding: 10px;
      margin-bottom: 10px;
      border-radius: 5px;
      border: 1px solid #ddd;
    }
    .edit-form button {
      background: #333;
      color: white;
      padding: 8px 12px;
      border-radius: 5px;
      border: none;
    }
    .edit-form button:hover {
      background: #555;
    }
    /* Стиль для кнопки удаления */
    .delete-button {
      background: none;
      border: none;
      color: #ff4444;
      font-size: 18px;
      cursor: pointer;
    }
    .delete-button:hover {
      color: #cc0000;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Отзывы на фильмы</h2>
  <c:if test="${empty reviews}">
    <p>Отзывов нет.</p>
  </c:if>
  <ul class="review-list">
    <c:forEach var="review" items="${reviews}">
      <li class="review-item">
        <p><strong>Фильм:</strong> ${review.getFilmTitle()}</p>
        <p><strong>Отзыв:</strong> ${review.getReviewText()}</p>

        <a href="/updateReview?filmId=${review.getFilmId()}">Редактировать </a>
        <form method="POST" style="display:inline;">
          <input type="hidden" name="reviewId" value="${review.getId()}">
          <button type="submit" class="delete-button" title="Удалить отзыв">Удалить</button>
        </form>
      </li>
    </c:forEach>
  </ul>
  <a class="back-button" href="${pageContext.servletContext.contextPath}/">Назад на главную</a>
</div>

<footer>
  <p>&copy; 2025 FilmCatalog. Все права защищены.</p>
</footer>

</body>
</html>