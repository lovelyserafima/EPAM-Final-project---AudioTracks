package com.epam.audiomanager.command.impl.common.main;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.user.TypeUser;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class MainCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        String page;
        if (httpServletRequest.getSession().getAttribute(ConstantAttributes.ROLE) == TypeUser.CLIENT){
            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT);
        } else {
            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN);
        }
        router.setPagePath(page);
        return router;
    }
}