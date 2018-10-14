package com.epam.audiomanager.command.impl.admin.audio;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.AudioLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import com.epam.audiomanager.util.valid.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class ConfirmEditingAudioCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        httpServletRequest.setAttribute(ConstantAttributes.WRONG_ALBUM, null);
        httpServletRequest.setAttribute(ConstantAttributes.WRONG_PRICE, null);
        httpServletRequest.setAttribute(ConstantAttributes.WRONG_YEAR, null);
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN_EDIT_AUDIO);

        int year = Integer.parseInt(httpServletRequest.getParameter(ConstantAttributes.YEAR));
        if (Validation.isCorrectYear(year)){
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(httpServletRequest.getParameter(
                    ConstantAttributes.PRICE)));
            if (Validation.isCorrectPrice(price)){
                String band = httpServletRequest.getParameter(ConstantAttributes.BAND);
                String album = httpServletRequest.getParameter(ConstantAttributes.ALBUM);
                Integer albumId = AudioLogic.isAlbumExists(album, band);
                if (albumId != null) {
                    String name = httpServletRequest.getParameter(ConstantAttributes.NAME);
                    String demoAudioPath = httpServletRequest.getParameter(ConstantAttributes.DEMO_AUDIO_PATH);
                    String fullAudioPath = httpServletRequest.getParameter(ConstantAttributes.FULL_AUDIO_PATH);
                    int audioId = ((AudioTrack) httpSession.getAttribute(ConstantAttributes.AUDIO_TRACK)).getId();
                    AudioLogic.updateAudioTrack(audioId, albumId, name, band, year, price, demoAudioPath, fullAudioPath);
                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN_READ_MORE_AUDIO);
                    AudioTrack audioTrack = new AudioTrack(audioId, name, band, year, price, fullAudioPath,
                            demoAudioPath, album);
                    httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACK, audioTrack);
                    httpSession.setAttribute(ConstantAttributes.AUDIO_TRACK, audioTrack);
                } else {
                    httpServletRequest.setAttribute(ConstantAttributes.WRONG_ALBUM,
                            messageManager.getMessage(ConstantMessages.WRONG_ALBUM));
                }
            } else {
                httpServletRequest.setAttribute(ConstantAttributes.WRONG_PRICE,
                        messageManager.getMessage(ConstantMessages.WRONG_PRICE));
            }
        } else {
            httpServletRequest.setAttribute(ConstantAttributes.WRONG_YEAR,
                    messageManager.getMessage(ConstantMessages.WRONG_YEAR));
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }
}
