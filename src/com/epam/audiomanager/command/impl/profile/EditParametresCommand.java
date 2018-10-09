package com.epam.audiomanager.command.impl.profile;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EditParametresCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_CHANGE_PARAMETRES));
        return router;
    }
}
