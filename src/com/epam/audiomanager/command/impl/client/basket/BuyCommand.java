package com.epam.audiomanager.command.impl.client.basket;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.BasketLogic;
import com.epam.audiomanager.logic.BuyLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class BuyCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_BASKET);

        Client client = (Client) httpSession.getAttribute(ConstantAttributes.USER);
        int clientId = client.getId();
        int audioId = Integer.parseInt(httpServletRequest.getParameter(ConstantAttributes.TRACK_ID));
        BigDecimal clientMoney = client.getMoney();
        BigDecimal priceAudio = BuyLogic.isEnoughMoney(clientMoney, audioId);

        if (priceAudio.doubleValue() > 0){
            BuyLogic.buyAudioTrack(clientId, audioId, clientMoney, priceAudio);
            client.setMoney(BigDecimal.valueOf(clientMoney.doubleValue() - priceAudio.doubleValue()));

            List<AudioTrack> leftAudioTracks = BasketLogic.findAllOrders(clientId);
            httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS, leftAudioTracks);
            httpSession.setAttribute(ConstantAttributes.AUDIO_TRACKS, leftAudioTracks);
            httpServletRequest.setAttribute(ConstantAttributes.RESULT_OF_SUCCESSFULL_BUYING,
                    messageManager.getMessage(ConstantMessages.TRACK_WAS_BOUGHT_SUCCESSFULLY));
            httpSession.setAttribute(ConstantAttributes.USER, client);
        } else {
            httpServletRequest.setAttribute(ConstantAttributes.RESULT_OF_WRONG_BUYING, messageManager.getMessage(
                    ConstantMessages.NOT_ENOUGH_MONEY));
            httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS, httpSession.getAttribute(
                    ConstantAttributes.AUDIO_TRACKS));
        }
        //router.setRouteTypeRedirect();
        router.setPagePath(page);
        return router;

    }
}
