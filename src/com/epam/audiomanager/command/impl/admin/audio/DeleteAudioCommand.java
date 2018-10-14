package com.epam.audiomanager.command.impl.admin.audio;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.exception.ProjectException;
import javax.servlet.http.HttpServletRequest;

public class DeleteAudioCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        return null;
    }
}
