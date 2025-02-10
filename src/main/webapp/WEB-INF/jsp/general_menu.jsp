<%--
  Created by IntelliJ IDEA.
  User: ann
  Date: 05.02.2025
  Time: 10:19
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
    <title>FileCatalog</title>
    <link rel="stylesheet" href="styles.css"> <!-- Ссылка на внешний CSS файл -->
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        header {
            width: 100%;
            background: #333;
            color: white;
            padding: 20px 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .logo h1 {
            margin-left: 20px;
        }
        nav ul {
            list-style: none;
            padding: 0;
            display: flex;
            gap: 20px;
        }
        nav ul li {
            display: inline;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }
        nav ul li a:hover {
            color: #ff9800;
        }
        .container {
            display: flex;
            justify-content: space-between;
            width: 80%;
            max-width: 1200px;
            margin-top: 40px;
            gap: 30px;
        }
        .description {
            flex: 0 0 70%;  /* 70% ширины для описания */
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .auth {
            flex: 0 0 30%;  /* 30% ширины для блока с авторизацией */
            background: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .auth h3 {
            margin-bottom: 15px;
        }
        .auth a {
            color: #333;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }
        .auth a:hover {
            color: #ff9800;
        }
        .auth .links {
            display: flex;
            flex-direction: column;
            gap: 15px;
            align-items: center;
        }
        .auth .links a {
            padding: 10px 20px;
            background-color: #ff9800;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            width: 100%;
            max-width: 200px;
            text-align: center;
            transition: background-color 0.3s ease;
        }
        .auth .links a:hover {
            background-color: #e68900;
        }
        footer {
            margin-top: 40px;
            padding: 20px;
            text-align: center;
            background: #333;
            color: white;
            width: 100%;
            position: relative;
            bottom: 0;
        }
        footer p {
            margin: 0;
        }
        img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<header>
    <div class="logo">
        <h1>Film Catalog</h1>
    </div>
    <nav>
        <ul>
            <li><a href="${pageContext.servletContext.contextPath}/all">Все фильмы</a></li>
            <c:if test="${sessionScope.get('userId') != null}">
                <li><a href="${pageContext.servletContext.contextPath}/addfilm">Добавить фильм</a></li>
            </c:if>
        </ul>
    </nav>
</header>

<div class="container">

    <div class="description">
        <h2>Добро пожаловать в Film Catalog!</h2>
        <p>Наш каталог содержит огромную базу фильмов с отзывами и рейтингами от пользователей. Вы можете находить лучшие фильмы, добавлять свои рецензии и обсуждать кино с другими киноманами.</p>
        <p>Зарегистрируйтесь, чтобы оставлять комментарии, добавлять любимые фильмы и участвовать в жизни сообщества.</p>
        <img src="${pageContext.request.contextPath}/imageServlet?image=info.jpg" alt="Description of image">
    </div>

    <div class="auth">
        <c:if test="${sessionScope.get('userId') == null}">
            <h3>Авторизация</h3>
            <p><a href="${pageContext.servletContext.contextPath}/authorization">Войти в аккаунт</a></p>
            <p>Еще нет аккаунта? <a href="${pageContext.servletContext.contextPath}/register">Зарегистрироваться</a></p>
        </c:if>
        <c:if test="${username != null}">
            <h3>Привет, <strong>${username}</strong>!</h3>
            <div class="links">
                <p><a href="${pageContext.servletContext.contextPath}/favourite">Избранные фильмы</a></p>
                <p><a href="${pageContext.servletContext.contextPath}/reviews">Мои отзывы</a></p>
            </div>
            <p></p>
            <a href="${pageContext.servletContext.contextPath}/exit">Выход</a>
        </c:if>
    </div>
</div>

<footer>
    <p>&copy; 2025 FilmCatalog. Все права защищены.</p>
</footer>
</body>
</html>
