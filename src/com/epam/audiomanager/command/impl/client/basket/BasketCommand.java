package com.epam.audiomanager.command.impl.client.basket;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.User;
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

public class BasketCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();

        int id = ((User) httpServletRequest.getSession().getAttribute(ConstantAttributes.USER)).getId();
        List<AudioTrack> audioTracks = BasketLogic.findAllOrders(id);

        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        if (!audioTracks.isEmpty()){
            httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS, audioTracks);
            httpSession.setAttribute(ConstantAttributes.AUDIO_TRACKS, audioTracks);
        } else {
            httpServletRequest.setAttribute(ConstantAttributes.RESULT_OF_WRONG_BUYING,
                    messageManager.getMessage(ConstantMessages.EMPTY_BASKET));
        }
        httpSession.setAttribute(ConstantAttributes.RESULT_OF_SUCCESSFULL_ACTION, null);
        httpSession.setAttribute(ConstantAttributes.RESULT_OF_WRONG_BUYING, null);
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_BASKET));
        return router;
    }
}
