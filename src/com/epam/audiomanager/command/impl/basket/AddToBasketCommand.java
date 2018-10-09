package com.epam.audiomanager.command.impl.basket;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.BasketLogic;
import com.epam.audiomanager.logic.MediaLibraryLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddToBasketCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        int clientId = ((User) httpSession.getAttribute(ConstantAttributes.USER)).getId();
        int audioId = Integer.parseInt(httpServletRequest.getParameter(ConstantAttributes.TRACK_ID));

        if (!BasketLogic.isOrderInBasket(clientId, audioId)){
            if (!MediaLibraryLogic.isTrackInMediaLibrary(clientId, audioId)){
                BasketLogic.insertOrder(clientId, audioId);
                httpServletRequest.setAttribute(ConstantAttributes.RESULT_OF_ADDING_TO_BASKET, messageManager.getMessage(
                        ConstantMessages.TRACK_WAS_ADDED_TO_BASKET));
            } else {
                httpServletRequest.setAttribute(ConstantAttributes.RESULT_OF_SEARCHING,
                        messageManager.getMessage(ConstantMessages.ALREADY_EXISTS_IN_MEDIA_LIBRARY));
            }
        } else {
            httpServletRequest.setAttribute(ConstantAttributes.RESULT_OF_SEARCHING, messageManager.getMessage(
                    ConstantMessages.ORDER_ALREADY_EXISTS_IN_BASKET));
        }

        httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS,
                httpSession.getAttribute(ConstantAttributes.AUDIO_TRACKS));
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_SEARCH));
        return router;
    }
}