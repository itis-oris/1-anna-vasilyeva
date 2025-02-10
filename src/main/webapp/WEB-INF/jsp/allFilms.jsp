<%--
  Created by IntelliJ IDEA.
  User: ann
  Date: 05.02.2025
  Time: 11:03
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
    <title>Все фильмы | FileCatalog</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"> <!-- подключаем внешний CSS -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background: #333;
            color: white;
            padding: 15px;
            text-align: center;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
        .favorite-btn {
            font-size: 24px; /* Увеличиваем размер сердца */
            background: transparent;
            border: none;
            cursor: pointer;
            padding: 5px;
            transition: transform 0.3s;
        }

        .favorite-btn:hover {
            transform: scale(1.2); /* Эффект увеличения при наведении */
        }

    </style>
</head>
<body>
<header>
    <h1>Film Catalog</h1>
</header>

<div class="container">
    <h2>Фильмы для просмотра</h2>
    <ul class="film-list">
        <c:forEach var="film" items="${films}">
            <li class="film-item">
                <div class="film-title">
                    <h3>${film.getTitle()}</h3>
                    <p>Автор: ${film.getAuthor()}</p>
                </div>
                <div class="film-actions">
                    <c:if test="${sessionScope.get('userId') != null}">
                        <a href="/addReviewToFilm?filmId=${film.getId()}">Добавить отзыв</a>
                    </c:if>
                    <a href="/reviewByFilm?filmId=${film.getId()}">Все отзывы</a>

                    <c:if test="${sessionScope.get('userId') != null}">
                        <button class="favorite-btn" data-film-id="${film.getId()}">
                        <c:choose>
                            <c:when test="${favoriteFilms.contains(film.getId())}">
                                ❤️
                            </c:when>
                            <c:otherwise>
                                🤍
                            </c:otherwise>
                        </c:choose>
                    </button>
                    </c:if>
                </div>
            </li>
        </c:forEach>

        <script>
            document.querySelectorAll('.favorite-btn').forEach(button => {
                button.addEventListener('click', function() {
                    let filmId = this.getAttribute('data-film-id');
                    let buttonElement = this;

                    fetch('/all', {
                        method: 'POST',
                        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                        body: 'filmId=' + filmId
                    }).then(response => response.json())
                        .then(data => {
                            if (data.status === "success") {
                                if (buttonElement.innerHTML === '❤️') {
                                    buttonElement.innerHTML = '🤍';  // Убираем из избранного, делаем белым
                                } else {
                                    buttonElement.innerHTML = '❤️';  // Добавляем в избранное, делаем красным
                                }
                            }
                        });
                });
            });
        </script>


    </ul>

    <a class="back-button" href="${pageContext.servletContext.contextPath}/">Назад на главную</a>

</div>

<footer>
    <p>&copy; 2025 FilmCatalog. Все права защищены.</p>
</footer>
</body>
</html>

