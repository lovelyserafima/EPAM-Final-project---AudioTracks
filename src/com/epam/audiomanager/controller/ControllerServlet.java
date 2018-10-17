package com.epam.audiomanager.controller;

import com.epam.audiomanager.command.ActionFactory;
import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.database.pool.ConnectionPool;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Router router;
        Command command = ActionFactory.defineCommand(request);

        try {
            router = command.execute(request);
            if (router.getPagePath() != null){
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(router.getPagePath());
                switch (router.getRouteType()){
                    case FORWARD:
                        requestDispatcher.forward(request, response);
                        break;
                    case REDIRECT:
                        response.sendRedirect(request.getContextPath() + router.getPagePath());
                        break;
                    default:
                        //log.error
                        throw new ProjectException("Unknown router type");
                }
            } else {
                String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
                response.sendRedirect(request.getContextPath() + page);
            }
        } catch (ProjectException e) {
            LOGGER.error("ProjectError", e);
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        AbandonedConnectionCleanupThread.checkedShutdown();//?
        ConnectionPool.getInstance().closePool();
    }
}