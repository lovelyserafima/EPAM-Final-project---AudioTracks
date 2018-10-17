package com.epam.audiomanager.command.impl.admin;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.AudioLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class ReadMoreCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();

        int audioId = Integer.parseInt(httpServletRequest.getParameter(ConstantAttributes.TRACK_ID));
        AudioTrack audioTrack = AudioLogic.findAudioTrack(audioId);

        httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACK, audioTrack);
        httpServletRequest.getSession().setAttribute(ConstantAttributes.AUDIO_TRACK, audioTrack);
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN_READ_MORE_AUDIO));
        return router;
    }
}