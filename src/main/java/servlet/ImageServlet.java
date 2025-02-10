package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.WebInitParam;

@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем имя изображения из параметра запроса
        String imageName = request.getParameter("image");
        if (imageName == null || imageName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Image name is required");
            return;
        }

        // Путь к изображению в папке ресурсов (например, в WEB-INF/images)
        String imagePath = getServletContext().getRealPath("/WEB-INF/images/" + imageName);

        // Открываем файл
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
            return;
        }

        // Получаем MIME тип изображения
        String mimeType = getServletContext().getMimeType(imageFile.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";  // Если тип не определен, ставим по умолчанию
        }

        // Устанавливаем заголовки для типа содержимого и длины контента
        response.setContentType(mimeType);
        response.setContentLength((int) imageFile.length());

        // Создаем поток для отправки изображения
        try (FileInputStream inputStream = new FileInputStream(imageFile);
             OutputStream outStream = response.getOutputStream()) {

            // Читаем изображение и отправляем его в ответ
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading image");
        }
    }
}

