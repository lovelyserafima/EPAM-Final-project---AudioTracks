package com.epam.audiomanager.command.impl.admin.add;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.audio.AudioTrack;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.AudioLogic;
import com.epam.audiomanager.logic.SearchMusicLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import com.epam.audiomanager.util.valid.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class ConfirmAddingNewTrack implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));

        String name = Validation.replaceScript(httpServletRequest.getParameter(ConstantAttributes.NAME));
        String band = Validation.replaceScript(httpServletRequest.getParameter(ConstantAttributes.BAND));
        String album = httpServletRequest.getParameter(ConstantAttributes.ALBUM);
        int year = Integer.parseInt(httpServletRequest.getParameter(ConstantAttributes.YEAR));
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_ADD_NEW_TRACK);

        httpServletRequest.setAttribute(ConstantAttributes.ALREADY_EXISTS_TRACK, null);
        httpServletRequest.setAttribute(ConstantAttributes.WRONG_PRICE, null);
        httpServletRequest.setAttribute(ConstantAttributes.WRONG_YEAR, null);

        if (Validation.isCorrectYear(year)){
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(httpServletRequest.getParameter(
                    ConstantAttributes.PRICE)));
            if (Validation.isCorrectPrice(price)){
                String fullAudioPath = Validation.replaceScript(httpServletRequest
                        .getParameter(ConstantAttributes.FULL_AUDIO_PATH));
                String demoAudioPath = Validation.replaceScript(httpServletRequest
                        .getParameter(ConstantAttributes.DEMO_AUDIO_PATH));
                if (!AudioLogic.isAudioTrackExists(name, band)){
                    Integer albumId;
                    if (!album.isEmpty()){
                        album = Validation.replaceScript(album);
                        albumId = AudioLogic.isAlbumExists(album, band);
                        if (albumId != null) {
                            AudioLogic.insertAudioTrack(albumId, name, band, year, price, fullAudioPath, demoAudioPath);
                            httpServletRequest.setAttribute(ConstantAttributes.GOOD_RESULT_OF_ADDING,
                                    messageManager.getMessage(ConstantMessages.TRACK_WAS_ADDED));
                            List<AudioTrack> audioTracks = SearchMusicLogic.findAllTracks();
                            httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS, audioTracks);
                            page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN_SEARCH);
                        } else {
                            httpServletRequest.setAttribute(ConstantAttributes.WRONG_ALBUM,
                                    messageManager.getMessage(ConstantMessages.WRONG_ALBUM));
                        }
                    } else {
                        AudioLogic.insertAudioTrackWithoutAlbum(name, band, year, price, fullAudioPath, demoAudioPath);
                        httpServletRequest.setAttribute(ConstantAttributes.GOOD_RESULT_OF_ADDING,
                                messageManager.getMessage(ConstantMessages.TRACK_WAS_ADDED));
                        List<AudioTrack> audioTracks = SearchMusicLogic.findAllTracks();
                        httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS, audioTracks);
                        page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN_SEARCH);
                    }
                } else {
                    httpServletRequest.setAttribute(ConstantAttributes.ALREADY_EXISTS_TRACK,
                            messageManager.getMessage(ConstantMessages.ALREADY_EXISTING_TRACK));
                }
            } else {
                httpServletRequest.setAttribute(ConstantAttributes.WRONG_PRICE,
                        messageManager.getMessage(ConstantMessages.WRONG_PRICE));
            }
        } else {
            httpServletRequest.setAttribute(ConstantAttributes.WRONG_YEAR,
                    messageManager.getMessage(ConstantMessages.WRONG_YEAR));
        }
        router.setPagePath(page);
        return router;
    }
}
