import database.DbSetup;
import servlet.*;
import dao.DAOLike;
import dao.DAOMessage;
import dao.DAOUser;
import filter.MessageFilter;
import filter.SessionFilter;
import filter.LoginFilter;
import heroku.HerokuEnv;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


public class TinderApp {
    public static void main(String[] args) throws Exception {

        DbSetup.migrate(HerokuEnv.jdbc_url(), HerokuEnv.jdbc_username(), HerokuEnv.jdbc_password());
        Server server = new Server(HerokuEnv.port());
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        TemplateEngine engine = TemplateEngine.resources("/templates");
        DAOUser daoUser = new DAOUser();
        DAOLike daoLike = new DAOLike();
        DAOMessage daoMessage = new DAOMessage();
        LoginService loginService = new LoginService(daoUser);
        UserService userService = new UserService(daoUser, daoLike);
        LikeService likeService = new LikeService(daoUser);
        MessageService messageService = new MessageService(daoMessage);
//      ==========================================================================================================
        handler.addServlet((new ServletHolder(new LoginServlet(engine, loginService))), "/login");
        handler.addServlet((new ServletHolder(
                new RegistrationServlet(engine, new RegisterService(daoUser)))), "/register");
        handler.addServlet((new ServletHolder(new LikeServlet(engine, likeService))), "/liked");
        handler.addServlet((new ServletHolder(
                new MessageServlet(engine, messageService, userService))), "/messages/*");
        handler.addServlet((new ServletHolder(new UserServlet(engine, userService))), "/users");
        handler.addServlet(BackToLoginServlet.class, "/*");

        handler.addServlet((new ServletHolder(
                new StaticContentServlet("src/main/resources/templates"))), "/static/*");
        handler.addFilter(new FilterHolder(
                new LoginFilter(engine, loginService)), "/login/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(SessionFilter.class, "/liked", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(SessionFilter.class, "/messages/{id}", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(SessionFilter.class, "/users", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(MessageFilter.class, "/messages/{id}", EnumSet.of(DispatcherType.REQUEST));
//      ==============================================================================================================
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
