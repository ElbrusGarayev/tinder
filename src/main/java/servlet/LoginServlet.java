package servlet;

import entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import service.LoginService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Log4j2
@AllArgsConstructor
public class LoginServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final LoginService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        engine.render("login.ftl", resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = service.getUser(req.getParameter("email"), req.getParameter("psw"));
        if (user != null){
            log("POST");
            service.updateLastSeen(user);
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect("/users");
    }
}
