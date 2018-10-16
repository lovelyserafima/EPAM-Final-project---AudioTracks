/*package com.epam.audiomanager.filter;

import com.epam.audiomanager.entity.user.TypeUser;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"}, servletNames = {"ControllerServlet"})
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession httpSession = httpRequest.getSession();
        User user = ((User) httpSession.getAttribute(ConstantAttributes.USER));

        String path = String.valueOf(((HttpServletRequest) httpRequest).getRequestURL());
        System.out.println(path);
        if (user == null && !path.equals("http://localhost:8080/controller?command=sign%20up")
             && !path.equals("http://localhost:8080/controller")
                && !path.equals("http://localhost:8080")){
            RequestDispatcher requestDispatcher = httpRequest.getServletContext()
                    .getRequestDispatcher(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN));
            requestDispatcher.forward(httpRequest, httpResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}*/
