package com.epam.audiomanager.command.impl.common.auth.registration;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.entity.user.TypeUser;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.registration.EmailLogic;
import com.epam.audiomanager.logic.registration.LoginLogic;
import com.epam.audiomanager.logic.registration.mail.EmailConfirmLogic;
import com.epam.audiomanager.util.ConfirmCodeGenerator;
import com.epam.audiomanager.util.Encryption;
import com.epam.audiomanager.util.constant.*;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import com.epam.audiomanager.util.valid.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class RegisterCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        String email = httpServletRequest.getParameter(ConstantAttributes.EMAIL);
        String password = httpServletRequest.getParameter(ConstantAttributes.PASSWORD);
        String samePassword = httpServletRequest.getParameter(ConstantAttributes.SAME_PASSWORD);
        String login = httpServletRequest.getParameter(ConstantAttributes.LOGIN);
        String firstName = httpServletRequest.getParameter(ConstantAttributes.FIRST_NAME);
        String secondName = httpServletRequest.getParameter(ConstantAttributes.SECOND_NAME);

        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute(ConstantAttributes.ERROR_NOT_SAME_PASSWORDS, null);
        httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, null);
        httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, null);
        httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, null);
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_REGISTRATION);
        if (Validation.isCorrectEmail(email)){
            if (Validation.isCorrectPassword(password)){
                if (Validation.arePasswordsEqual(password, samePassword)){
                        if (!EmailLogic.isEmailExists(email)){
                            if (Validation.isCorrectLogin(login)) {
                                login = Validation.replaceScript(login);
                                firstName = Validation.replaceScript(firstName);
                                secondName = Validation.replaceScript(secondName);
                                if (!LoginLogic.isLoginExists(login)) {
                                    Client client = new Client(email, login, firstName, secondName,
                                            TypeUser.CLIENT, false);

                                    httpSession.setAttribute(ConstantAttributes.CLIENT, client);
                                    httpSession.setAttribute(ConstantAttributes.ENCRYPTED_PASSWORD,
                                            Encryption.encryptPassword(password));

                                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_CONFIRM);

                                    String confirmCode = ConfirmCodeGenerator.generateConfirmCode();
                                    httpSession.setAttribute(ConstantAttributes.CONFIRM_CODE, confirmCode);

                                    ResourceBundle resourceBundle = ResourceBundle.getBundle(ConstantBundles.MAIL);
                                    new EmailConfirmLogic(email, ConstantValues.SUBJECT, confirmCode,
                                            resourceBundle).start();
                                } else {
                                    httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN,
                                            messageManager.getMessage(ConstantMessages.PATH_ERROR_EXISTING_LOGIN));
                                }
                            } else {
                                httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_LOGIN, messageManager.getMessage(
                                        ConstantMessages.PATH_ERROR_WRONG_LOGIN));
                            }
                        } else {
                            httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, messageManager.getMessage(
                                    ConstantMessages.PATH_ERROR_EXISTING_EMAIL));
                        }
                } else {
                    httpSession.setAttribute(ConstantAttributes.ERROR_NOT_SAME_PASSWORDS, messageManager.getMessage(
                            ConstantMessages.PATH_ERROR_NOT_THE_SAME_PASSWORDS));
                }
            } else {
                httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_PASSWORD, messageManager.getMessage(
                        ConstantMessages.PATH_ERROR_WRONG_TYPE_OF_PASSWORD));
            }
        } else {
            httpSession.setAttribute(ConstantAttributes.ERROR_WRONG_EMAIL, messageManager.getMessage(
                    ConstantMessages.PATH_ERROR_WRONG_EMAIL));
        }
        router.setPagePath(page);
        return router;
    }
}
