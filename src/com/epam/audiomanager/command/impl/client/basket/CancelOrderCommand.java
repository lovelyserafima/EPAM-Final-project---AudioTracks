package com.epam.audiomanager.command.impl.client.basket;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.BasketLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CancelOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        Client client = (Client) httpSession.getAttribute(ConstantAttributes.USER);
        int clientId = client.getId();
        int audioId = Integer.parseInt(httpServletRequest.getParameter(ConstantAttributes.TRACK_ID));
        BasketLogic.cancelOrder(clientId, audioId);
        httpServletRequest.setAttribute(ConstantAttributes.RESULT_OF_SUCCESSFULL_ACTION,
                messageManager.getMessage(ConstantMessages.ORDER_WAS_SUCCESSFULLY_CANCELED));

        List<AudioTrack> leftAudioTracks = BasketLogic.findAllOrders(clientId);
        httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS, leftAudioTracks);

        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_BASKET));
        return router;
    }
}
