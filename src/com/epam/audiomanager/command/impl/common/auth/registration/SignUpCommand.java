package com.epam.audiomanager.command.impl.common.auth.registration;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_REGISTRATION);
        router.setPagePath(page);
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, null);
        httpSession.setAttribute(ConstantAttributes.ERROR_NOT_SAME_PASSWORDS, null);
        return router;
    }
}
