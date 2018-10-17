package com.epam.audiomanager.command.impl.admin.add;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.AlbumLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantMessages;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import com.epam.audiomanager.util.property.MessageManager;
import com.epam.audiomanager.util.valid.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class ConfirmAddingNewAlbum implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        String name = Validation.replaceScript(httpServletRequest.getParameter(ConstantAttributes.NAME));
        String band = Validation.replaceScript(httpServletRequest.getParameter(ConstantAttributes.BAND));
        int year = Integer.parseInt(httpServletRequest.getParameter(ConstantAttributes.YEAR));
        HttpSession httpSession = httpServletRequest.getSession();

        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(
                ConstantAttributes.CHANGE_LANGUAGE));
        String page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_ADD_NEW_ALBUM);

        httpServletRequest.setAttribute(ConstantAttributes.ALREADY_EXISTS_ALBUM, null);
        httpServletRequest.setAttribute(ConstantAttributes.WRONG_PRICE, null);
        httpServletRequest.setAttribute(ConstantAttributes.WRONG_YEAR, null);

        if (Validation.isCorrectYear(year)){
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(httpServletRequest.getParameter(
                    ConstantAttributes.PRICE)));
            if (Validation.isCorrectPrice(price)){
                if (!AlbumLogic.isAlbumExists(name, band, year)){
                    AlbumLogic.addNewAlbum(name, band, year, price);
                    httpServletRequest.setAttribute(ConstantAttributes.GOOD_RESULT_OF_ADDING,
                            messageManager.getMessage(ConstantMessages.ALBUM_WAS_ADDED));
                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN_SEARCH);
                    httpServletRequest.setAttribute(ConstantAttributes.AUDIO_TRACKS,
                            httpSession.getAttribute(ConstantAttributes.AUDIO_TRACKS));
                } else {
                    httpServletRequest.setAttribute(ConstantAttributes.ALREADY_EXISTS_ALBUM,
                            messageManager.getMessage(ConstantMessages.ALREADY_EXISTING_ALBUM));
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
