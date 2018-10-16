package com.epam.audiomanager.command.impl.admin;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.user.User;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.MediaLibraryLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class WhoListensCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN_WHO_LISTENS));
        MessageManager messageManager = MessageManager.defineLocale((String) httpServletRequest.getSession().getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        int audioId = ((AudioTrack)httpServletRequest.getSession().getAttribute(ConstantAttributes.AUDIO_TRACK)).getId();
        List<User> userList = MediaLibraryLogic.findAllUserHavingTracks(audioId);
        if (!userList.isEmpty()){
            httpServletRequest.setAttribute(ConstantAttributes.USERS, userList);
        } else {
            httpServletRequest.setAttribute(ConstantAttributes.NO_USERS, messageManager.getMessage(
                    ConstantMessages.NOBODY_BOUGHT_IT));
        }
        return router;
    }
}
