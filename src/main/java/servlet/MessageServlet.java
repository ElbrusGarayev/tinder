package servlet;

import entity.Message;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import service.MessageService;
import service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Log4j2
@AllArgsConstructor
public class MessageServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final MessageService service;
    private final UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession();
        User sender = (User) session.getAttribute("user");
        if (sender == null) resp.sendRedirect("/login");
        else {
            log("GET");
            int receiverId = Integer.parseInt(req.getPathInfo().replace("/", ""));
            List<Message> messages = service.getAllMessages(sender.getId(), receiverId);
            data.put("messages", messages);
            data.put("sender", sender.getId());
            data.put("receiver", userService.getUser(receiverId));
            engine.render("chat.ftl", data, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int receiverId = Integer.parseInt(req.getPathInfo().replace("/", ""));
        String content = (req.getParameter("content")).trim();
        User sender = (User) session.getAttribute("user");
        if (!content.isEmpty()){
            log("POST");
            service.addMessage(new Message(sender.getId(), receiverId, content));
        }
        resp.sendRedirect("/messages" + req.getPathInfo());
    }
}
