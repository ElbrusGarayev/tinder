package servlet;

import entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import service.LikeService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Log4j2
@AllArgsConstructor
public class LikeServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final LikeService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<User> likedUsers = service.getLikedUsers(user.getId());
        log("GET");
        data.put("users", likedUsers);
        engine.render("people-list.ftl", data, resp);
    }
}
