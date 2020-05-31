package servlet;

import lombok.AllArgsConstructor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@AllArgsConstructor
public class StaticContentServlet extends HttpServlet {
    private final String baseFolder;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Path path = Paths.get(baseFolder, req.getPathInfo());
        ServletOutputStream os = resp.getOutputStream();
        Files.copy(path, os);
    }
}
