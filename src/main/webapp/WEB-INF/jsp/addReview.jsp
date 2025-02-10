<%--
  Created by IntelliJ IDEA.
  User: ann
  Date: 06.02.2025
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Добавить отзыв | FileCatalog</title>
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
      text-align: center;
    }
    textarea {
      width: 90%;
      height: 150px;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
      resize: none;
    }
    button {
      background: #333;
      color: white;
      border: none;
      padding: 10px 15px;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
    }
    button:hover {
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
  <h2>Добавить отзыв</h2>
  <form method="POST">
    <label for="reviewText">Ваш отзыв:</label>
    <textarea id="reviewText" name="reviewText" required></textarea>
    <button type="submit">Отправить</button>
  </form>
  <a class="back-button" href="${pageContext.servletContext.contextPath}/">Назад на главную</a>
</div>

<footer>
  <p>&copy; 2025 FilmCatalog. Все права защищены.</p>
</footer>
</body>
</html>
