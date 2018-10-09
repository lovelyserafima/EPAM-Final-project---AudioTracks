package com.epam.audiomanager.command.impl.medialibrary;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
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
import java.util.List;

public class MediaLibraryCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_MEDIA_LIBRARY);
        int id = ((User) httpServletRequest.getSession().getAttribute(ConstantAttributes.USER)).getId();
        List<AudioTrack> audioTracks = MediaLibraryLogic.findAllUserTracks(id);
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));
        if (!audioTracks.isEmpty()){
            httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS, audioTracks);
        } else {
            httpServletRequest.setAttribute(ConstantAttributes.NO_TRACKS,
                    messageManager.getMessage(ConstantMessages.NO_TRACKS));
        }
        router.setPagePath(page);
        return router;
    }
}
