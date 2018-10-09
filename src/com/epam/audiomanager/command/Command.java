package com.epam.audiomanager.command;

import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.exception.ProjectException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest httpServletRequest) throws ProjectException;
}
