package com.epam.audiomanager.command.impl.main;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class MainCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT);
        router.setPagePath(page);
        return router;
    }
}