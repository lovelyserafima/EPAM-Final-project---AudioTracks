package com.epam.audiomanager.command.impl.profile;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.ChangeParametresLogic;
import com.epam.audiomanager.logic.registration.EmailLogic;
import com.epam.audiomanager.logic.registration.LoginLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConfirmEditingParametresCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));
        Client user = (Client) httpSession.getAttribute(ConstantAttributes.USER);
        String enteredEmail = httpServletRequest.getParameter(ConstantAttributes.EMAIL);
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_CHANGE_PARAMETRES);
        httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, null);
        httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, null);
        if (!EmailLogic.isEmailExists(enteredEmail)){
            String oldLogin = user.getLogin();
            String enteredLogin = httpServletRequest.getParameter(ConstantAttributes.LOGIN);
            if (!oldLogin.equals(enteredLogin)){
                if (!LoginLogic.isLoginExists(enteredLogin)){
                    prepareParametres(user, enteredEmail, enteredLogin, httpServletRequest, httpSession, page);
                    ChangeParametresLogic.changeParametresLogic(user, oldLogin);
                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_PROFILE);
                } else {
                    httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN,
                            messageManager.getMessage(ConstantMessages.PATH_ERROR_EXISTING_LOGIN));
                }
            } else {
                prepareParametres(user, enteredEmail, enteredLogin, httpServletRequest, httpSession, page);
            }
        } else {
            httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, messageManager.getMessage(
                    ConstantMessages.PATH_ERROR_EXISTING_EMAIL));
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private void prepareParametres(Client user, String enteredEmail, String enteredLogin,
                                   HttpServletRequest httpServletRequest, HttpSession httpSession, String page) {
        user.setEmail(enteredEmail);
        user.setLogin(enteredLogin);
        user.setFirstName(httpServletRequest.getParameter(ConstantAttributes.FIRST_NAME));
        user.setSecondName(httpServletRequest.getParameter(ConstantAttributes.SECOND_NAME));
        httpSession.setAttribute(ConstantAttributes.USER, user);
        httpSession.setAttribute(ConstantAttributes.LOGIN, user.getLogin());
    }
}
