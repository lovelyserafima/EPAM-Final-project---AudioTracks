package com.epam.audiomanager.command.impl;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        httpServletRequest.getSession().invalidate();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN));
        return router;
    }
}