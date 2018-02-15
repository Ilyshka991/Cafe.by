package bsuir.pechuro.servlet.filter;

import bsuir.pechuro.entity.User;
import bsuir.pechuro.enumeration.CommandName;
import bsuir.pechuro.enumeration.JspPageName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter",
        urlPatterns = {"/cafe.by/*"})
public class SecurityFilter implements Filter {


    public void init(FilterConfig fConfig) {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String uri = ((HttpServletRequest) request).getRequestURI();
            String command;
            if (uri.contains(".jpg")) {
                return;
            } else {
                command = uri.replace("/cafe.by/", "");
            }
            CommandName commandName = CommandName.valueOf(command.toUpperCase());
            String commandRole;
            if (commandName == null) {
                commandRole = "none";
            } else {
                commandRole = commandName.getRole();
            }
            if (!commandRole.equals("none")) {
                User user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
                String role;
                if (user == null) {
                    role = "all";
                } else {
                    role = user.getRole();
                }
                if (!role.equals(commandRole) && !commandRole.equals("all")) {
                    ((HttpServletResponse) response).sendRedirect("/cafe.by/index");
                    return;
                }
            }
        } catch (IllegalArgumentException e) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(JspPageName.ERROR.getPath());
            dispatcher.forward(req, resp);
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
