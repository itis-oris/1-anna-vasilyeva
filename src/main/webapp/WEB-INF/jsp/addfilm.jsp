<%--
  Created by IntelliJ IDEA.
  User: ann
  Date: 05.02.2025
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Добавить фильм | FileCatalog</title>
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
        input[type="text"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
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
        .back-link {
            margin-top: 10px;
            display: block;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Добавить фильм</h2>
    <form method="POST" action="addfilm">
        <label for="name">Название фильма:</label>
        <input type="text" id="name" name="name" required>
        <label for="author">Автор:</label>
        <input type="text" id="author" name="author" required>
        <button type="submit">Добавить</button>
    </form>
    <a class="back-link" href="${pageContext.servletContext.contextPath}/">Назад на главную</a>
</div>
</body>
</html>
