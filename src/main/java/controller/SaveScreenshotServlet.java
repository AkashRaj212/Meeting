package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/save-screenshot")
public class SaveScreenshotServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the base64-encoded image data from the request
        String imageData = request.getParameter("image");

        // Decode the image data from base64 to a byte array
        byte[] imageBytes = Base64.getDecoder().decode(imageData.split(",")[1]);

        String filePath = "a"/* path to save the image */;

        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(imageBytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}