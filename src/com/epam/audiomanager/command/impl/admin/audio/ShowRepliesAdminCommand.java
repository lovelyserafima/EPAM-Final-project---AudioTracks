package com.epam.audiomanager.command.impl.admin.audio;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.entity.audio.Reply;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.ReplyLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowRepliesAdminCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_SHOW_REPLIES_ADMIN));
        MessageManager messageManager = MessageManager.defineLocale((String) httpServletRequest.getSession().getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        int audioId = ((AudioTrack)httpServletRequest.getSession().getAttribute(ConstantAttributes.AUDIO_TRACK)).getId();
        List<Reply> replies = ReplyLogic.findReplies(audioId);
        if (!replies.isEmpty()){
            httpServletRequest.setAttribute(ConstantAttributes.REPLIES, replies);
        } else {
            httpServletRequest.setAttribute(ConstantAttributes.NO_REPLIES, messageManager.getMessage(
                    ConstantMessages.NO_REPLIES));
        }
        return router;
    }
}
