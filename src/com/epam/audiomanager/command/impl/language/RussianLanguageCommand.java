package com.epam.audiomanager.command.impl.language;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantLocales;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class RussianLanguageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        httpServletRequest.setAttribute(ConstantAttributes.CHANGE_LANGUAGE, ConstantLocales.RUSSIAN_LOCALE);
        httpServletRequest.getSession().setAttribute(ConstantAttributes.CHANGE_LANGUAGE, ConstantLocales.RUSSIAN_LOCALE);
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN));
        return router;
    }
}
