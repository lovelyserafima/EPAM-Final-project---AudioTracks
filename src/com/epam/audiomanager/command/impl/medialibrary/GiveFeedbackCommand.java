package com.epam.audiomanager.command.impl.medialibrary;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.ReplyLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GiveFeedbackCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        Client client = (Client) httpSession.getAttribute(ConstantAttributes.USER);
        int clientId = client.getId();
        int audioId = Integer.parseInt(httpServletRequest.getParameter(ConstantAttributes.TRACK_ID));
        String page;
        if (!ReplyLogic.isFeedbackExists(clientId, audioId)){
            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_GIVE_FEEDBACK);
            httpSession.setAttribute(ConstantAttributes.TRACK_ID, audioId);
        } else {
            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_MEDIA_LIBRARY);
            httpServletRequest.setAttribute(ConstantAttributes.BAD_RESULT_OF_REPLY, messageManager.getMessage(
                    ConstantMessages.ALREADY_EXISTS_FEEDBACK));
            httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS, httpSession.getAttribute(
                    ConstantAttributes.MEDIA_LIBRARY_TRACKS));
        }
        router.setPagePath(page);
        return router;
    }
}
