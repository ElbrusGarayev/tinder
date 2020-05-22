package servlet;

import entity.Like;
import entity.User;
import service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class UserServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final UserService service;

    public UserServlet(TemplateEngine engine, UserService service) {
        this.engine = engine;
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        User selected = service.getUserToDisplay(user);
        if (selected != null) {
            data.put("status", selected.getStatus());
            data.put("image_url", selected.getUrl());
            data.put("name", selected.getName());
            data.put("surname", selected.getSurname());
            engine.render("like-page.ftl", data, resp);
        } else {
            resp.sendRedirect("/liked");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String button = req.getParameter("button");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        User selected = service.getUserToDisplay(user);
        System.out.println("post");
        if (button.equals("dislike") && selected != null) {
            service.addLike(new Like(user.getId(), selected.getId(), false));
            resp.sendRedirect("/users");
        } else if (button.equals("like") && selected != null) {
            service.addLike(new Like(user.getId(), selected.getId(), true));
            resp.sendRedirect("/users");
        } else if(button.equals("likes"))
            resp.sendRedirect("liked");
        else if(button.equals("logout")){
            session.removeAttribute("user");
            resp.sendRedirect("login");
        }
        else {
            resp.sendRedirect("/liked");
        }
    }
}
