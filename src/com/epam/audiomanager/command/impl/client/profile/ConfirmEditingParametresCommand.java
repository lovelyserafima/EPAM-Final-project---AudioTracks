package com.epam.audiomanager.command.impl.client.profile;

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
import com.epam.audiomanager.util.valid.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConfirmEditingParametresCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));
        Client user = (Client) httpSession.getAttribute(ConstantAttributes.USER);
        String oldEmail = user.getEmail();
        String enteredEmail = httpServletRequest.getParameter(ConstantAttributes.EMAIL);
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_CHANGE_PARAMETRES);
        httpServletRequest.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, null);
        httpServletRequest.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, null);
        if (!oldEmail.equals(enteredEmail)) {
            if (!EmailLogic.isEmailExists(enteredEmail)) {
                page = checkParametres(user, httpServletRequest, enteredEmail, page, messageManager);
            } else {
                httpServletRequest.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, messageManager.getMessage(
                        ConstantMessages.PATH_ERROR_EXISTING_EMAIL));
            }
        } else {
            page = checkParametres(user, httpServletRequest, enteredEmail, page, messageManager);
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String checkParametres(Client user, HttpServletRequest httpServletRequest, String enteredEmail, String page,
                                   MessageManager messageManager)
            throws ProjectException {
        String oldLogin = user.getLogin();
        String enteredLogin = Validation.replaceScript(httpServletRequest.getParameter(ConstantAttributes.LOGIN));
        if (!oldLogin.equals(enteredLogin)) {
            if (!LoginLogic.isLoginExists(enteredLogin)) {
                prepareParametres(user, enteredEmail, enteredLogin, httpServletRequest);
                ChangeParametresLogic.changeParametresLogic(user, oldLogin);
                page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_PROFILE);
            } else {
                httpServletRequest.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN,
                        messageManager.getMessage(ConstantMessages.PATH_ERROR_EXISTING_LOGIN));
            }
        } else {
            prepareParametres(user, enteredEmail, enteredLogin, httpServletRequest);
            ChangeParametresLogic.changeParametresLogic(user, oldLogin);
            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_PROFILE);
        }
        return page;
    }

    private void prepareParametres(Client user, String enteredEmail, String enteredLogin,
                                   HttpServletRequest httpServletRequest) {
        user.setEmail(enteredEmail);
        user.setLogin(enteredLogin);
        user.setFirstName(Validation.replaceScript(httpServletRequest.getParameter(ConstantAttributes.FIRST_NAME)));
        user.setSecondName(Validation.replaceScript(httpServletRequest.getParameter(ConstantAttributes.SECOND_NAME)));
        httpServletRequest.setAttribute(ConstantAttributes.USER, user);
        httpServletRequest.setAttribute(ConstantAttributes.LOGIN, user.getLogin());
    }
}
