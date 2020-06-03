package servlet;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import service.RegisterService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Log4j2
@AllArgsConstructor
public class RegistrationServlet extends HttpServlet {
    private TemplateEngine engine;
    private final RegisterService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        engine.render("signup.ftl", resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> data = new HashMap<>();
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String status = req.getParameter("status");
        String password = req.getParameter("psw");
        String repeat = req.getParameter("psw-repeat");
        String url = req.getParameter("url");
        String message = service.registerUser(email, name, surname, status, password, repeat, url);
        if (message.equals("Registered")) {
            log("POST");
            resp.sendRedirect("/login");
        } else {
            log("POST");
            data.put("message", message);
        }
        engine.render("signup.ftl", data, resp);
    }
}
