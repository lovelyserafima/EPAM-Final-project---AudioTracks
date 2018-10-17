package com.epam.audiomanager.command.impl.common.auth;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.SignInLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import com.epam.audiomanager.util.valid.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));
        httpServletRequest.setAttribute(ConstantAttributes.ERROR_SIGN_IN_MESSAGE, null);

        String email = httpServletRequest.getParameter(ConstantAttributes.EMAIL);
        String password = httpServletRequest.getParameter(ConstantAttributes.PASSWORD);

        String page;
        if (Validation.isCorrectEmail(email) && Validation.isCorrectPassword(password)) {
            User user = SignInLogic.isUserExists(email, password);
            if (user != null) {
                if (user.getClass() == Client.class) {
                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT);
                } else {
                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN);
                }
                httpSession.setAttribute(ConstantAttributes.ROLE, user.getType());
                httpSession.setAttribute(ConstantAttributes.LOGIN, user.getLogin());
                httpSession.setAttribute(ConstantAttributes.USER, user);
                httpSession.setAttribute(ConstantAttributes.PASSWORD, password);
            } else {
                page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
                httpServletRequest.setAttribute(ConstantAttributes.ERROR_SIGN_IN_MESSAGE,
                        messageManager.getMessage(ConstantMessages.PATH_ERROR_SIGN_IN_MESSAGE));
            }
        } else {
            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
            httpServletRequest.setAttribute(ConstantAttributes.ERROR_SIGN_IN_MESSAGE,
                    messageManager.getMessage(ConstantMessages.PATH_ERROR_SIGN_IN_MESSAGE));
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }
}
