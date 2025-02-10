<%--
  Created by IntelliJ IDEA.
  User: ann
  Date: 07.02.2025
  Time: 13:06
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
  <title>Вход | FileCatalog</title>
  <link rel="stylesheet" href="styles.css"> <!-- Ссылка на внешний CSS файл -->
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      align-items: center;
      background-color: #f4f4f4;
    }
    .container {
      margin-top: 50px;
      padding: 20px;
      background: white;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      width: 350px;
      text-align: center;
    }
    input[type="text"], input[type="password"] {
      width: 90%;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    input[type="submit"] {
      background: #333;
      color: white;
      border: none;
      padding: 10px 15px;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
    }
    input[type="submit"]:hover {
      background: #555;
    }
    .register-link {
      margin-top: 10px;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Вход</h2>
  <form method="POST" action="authorization">
    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>
    <input type="submit" value="Войти">
  </form>
  <p class="register-link">Нет аккаунта? <a href="${pageContext.servletContext.contextPath}/register">Зарегистрироваться</a></p>
</div>
</body>
</html>
