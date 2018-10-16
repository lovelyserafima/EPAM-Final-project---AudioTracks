package com.epam.audiomanager.command.impl.admin;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.AudioLogic;
import com.epam.audiomanager.logic.SearchMusicLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteAudioCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN_SEARCH));

        int audioId = ((AudioTrack)httpServletRequest.getSession().getAttribute(ConstantAttributes.AUDIO_TRACK)).getId();
        AudioLogic.deleteAudioTrack(audioId);
        List<AudioTrack> newAudioTracks = SearchMusicLogic.findAllTracks();
        httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS,
                newAudioTracks);
        httpServletRequest.getSession().setAttribute(ConstantAttributes.AUDIO_TRACKS, newAudioTracks);
        return router;
    }
}
