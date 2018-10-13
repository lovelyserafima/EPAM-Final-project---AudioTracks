package com.epam.audiomanager.command.impl.client.profile;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.ChangeParametresLogic;
import com.epam.audiomanager.util.Encryption;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import com.epam.audiomanager.util.valid.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConfirmEditingPasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));
        String oldPassword = String.valueOf(httpSession.getAttribute(ConstantAttributes.PASSWORD));
        String enteredOldPassword = httpServletRequest.getParameter(ConstantAttributes.PASSWORD);
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_CHANGE_PASSWORD);
        httpSession.setAttribute(ConstantAttributes.ERROR_NOT_SAME_PASSWORDS, null);
        httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, null);
        httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_TYPE_OF_PASSWORD, null);
        if (oldPassword.equals(enteredOldPassword)){
            String newPassword = httpServletRequest.getParameter(ConstantAttributes.NEW_PASSWORD);
            if (Validation.isCorrectPassword(newPassword)){
                if (Validation.arePasswordsEqual(newPassword, httpServletRequest.getParameter(
                        ConstantAttributes.SAME_PASSWORD))){
                    String login = ((User) httpSession.getAttribute(ConstantAttributes.USER)).getLogin();
                    ChangeParametresLogic.changePassword(login, Encryption.encryptPassword(newPassword));
                    httpSession.setAttribute(ConstantAttributes.PASSWORD, newPassword);
                    httpSession.setAttribute(ConstantAttributes.RESULT_CHANGING,
                            messageManager.getMessage(ConstantMessages.PATH_RESULT_CHANGING_PASSWORD));
                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_PROFILE);
                } else {
                    httpSession.setAttribute(ConstantAttributes.ERROR_NOT_SAME_PASSWORDS,
                            messageManager.getMessage(ConstantMessages.PATH_ERROR_NOT_THE_SAME_PASSWORDS));
                }
            } else {
                httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_TYPE_OF_PASSWORD,
                        messageManager.getMessage(ConstantMessages.PATH_ERROR_WRONG_TYPE_OF_PASSWORD));
            }
        } else {
            httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD,
                    messageManager.getMessage(ConstantMessages.PATH_ERROR_NOT_EXISTING_PASSWORD));
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }
}
