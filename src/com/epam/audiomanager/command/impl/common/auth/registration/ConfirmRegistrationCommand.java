package com.epam.audiomanager.command.impl.common.auth.registration;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.registration.RegisterLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConfirmRegistrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        String submittedCode = httpServletRequest.getParameter(ConstantAttributes.CONFIRM_CODE);
        HttpSession httpSession = httpServletRequest.getSession();
        String realCode = String.valueOf(httpSession.getAttribute(ConstantAttributes.CONFIRM_CODE)).trim();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        String page;
        if (submittedCode.equals(realCode)){
            Client client = (Client) httpSession.getAttribute(ConstantAttributes.CLIENT);
            String encryptedPassword = String.valueOf(httpSession.getAttribute(ConstantAttributes.ENCRYPTED_PASSWORD));
            RegisterLogic.registerNewClient(client, encryptedPassword);
            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT);
            httpSession.setAttribute(ConstantAttributes.LOGIN, client.getLogin());
            httpSession.setAttribute(ConstantAttributes.USER, client);
        } else {
            httpSession.setAttribute(ConstantAttributes.ERROR_CONFIRM_CODE,
                    messageManager.getMessage(ConstantMessages.PATH_ERROR_WRONG_CONFIRM_CODE));
            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_CONFIRM);
        }
        router.setPagePath(page);
        return router;
    }
}
