package servlet;

import service.RegisterService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class RegistrationServlet extends HttpServlet {
    private final RegisterService service;
    private TemplateEngine engine;

    public RegistrationServlet(TemplateEngine engine, RegisterService service) {
        this.engine = engine;
        this.service = service;
    }

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
        data.put("message", message);
        engine.render("signup.ftl", data, resp);
    }
}