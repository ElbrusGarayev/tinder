package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getMethod().equalsIgnoreCase("GET")) {
            if (req.getPathInfo().equalsIgnoreCase("/"))
                resp.sendRedirect("/list/");
            else
                filterChain.doFilter(req, resp);
        } else
            filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
