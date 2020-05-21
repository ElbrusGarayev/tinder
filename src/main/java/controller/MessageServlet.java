package controller;

import entity.Like;
import entity.Message;
import entity.User;
import lombok.AllArgsConstructor;
import org.eclipse.jetty.server.session.Session;
import service.MessageService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        int receiverId = Integer.parseInt(req.getPathInfo().replace("/", ""));
        //User receiver = userService.getUser(receiverId);
        List<Message> messages = service.getAllMessages(sender.getId(), receiverId);
        messages.forEach(el -> System.out.println(el.toString()));
        data.put("messages", messages);
        data.put("sender", sender.getId());
        engine.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int receiverId = Integer.parseInt(req.getPathInfo().replace("/", ""));
        String text = req.getParameter("content");
        User sender = (User) session.getAttribute("user");
        service.addMessage(new Message(sender.getId(), receiverId, text));
        resp.sendRedirect("/messages" + req.getPathInfo());
    }
}
