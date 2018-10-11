package com.epam.audiomanager.command.impl.search;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.SearchMusicLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SearchMusicCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        String searchingItem = httpServletRequest.getParameter(ConstantAttributes.SEARCHING_ITEM);
        List<AudioTrack> audioTracks = SearchMusicLogic.findTracksByEntity(searchingItem);
        if (!audioTracks.isEmpty()){
            ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_SEARCH);
            httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS, audioTracks);
            httpServletRequest.getSession().setAttribute(ConstantAttributes.AUDIO_TRACKS, audioTracks);
        } else {
            HttpSession httpSession = httpServletRequest.getSession();
            MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                    ConstantAttributes.CHANGE_LANGUAGE));
            httpSession.setAttribute(ConstantAttributes.AUDIO_TRACKS, null);
            httpSession.setAttribute(ConstantAttributes.RESULT_OF_SEARCHING, messageManager.getMessage(
                    ConstantMessages.NO_RESULTS));
        }
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_SEARCH));
        return router;
    }
}
