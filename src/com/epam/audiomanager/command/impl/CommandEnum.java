package com.epam.audiomanager.command.impl;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.command.impl.auth.SignInCommand;
import com.epam.audiomanager.command.impl.basket.AddToBasketCommand;
import com.epam.audiomanager.command.impl.basket.BasketCommand;
import com.epam.audiomanager.command.impl.basket.BuyCommand;
import com.epam.audiomanager.command.impl.language.EnglishLanguageCommand;
import com.epam.audiomanager.command.impl.language.RussianLanguageCommand;
import com.epam.audiomanager.command.impl.main.MainCommand;
import com.epam.audiomanager.command.impl.medialibrary.ConfirmFeedbackCommand;
import com.epam.audiomanager.command.impl.medialibrary.GiveFeedbackCommand;
import com.epam.audiomanager.command.impl.medialibrary.MediaLibraryCommand;
import com.epam.audiomanager.command.impl.profile.*;
import com.epam.audiomanager.command.impl.auth.registration.ConfirmRegistrationCommand;
import com.epam.audiomanager.command.impl.auth.registration.RegisterCommand;
import com.epam.audiomanager.command.impl.auth.registration.SignUpCommand;
import com.epam.audiomanager.command.impl.search.SearchCommand;
import com.epam.audiomanager.command.impl.search.SearchMusicCommand;

public enum CommandEnum {
    SIGN_IN(new SignInCommand()), SIGN_UP(new SignUpCommand()), ENGLISH(new EnglishLanguageCommand()),
    RUSSIAN(new RussianLanguageCommand()), REGISTER(new RegisterCommand()), CONFIRM(new ConfirmRegistrationCommand()),
    SEARCH(new SearchCommand()), MAIN(new MainCommand()), LOG_OUT(new LogOutCommand()), PROFILE(new ProfileCommand()),
    EDIT_PASSWORD(new EditPasswordCommand()), CONFIRM_EDITING_PASSWORD(new ConfirmEditingPasswordCommand()),
    SEARCH_MUSIC(new SearchMusicCommand()), EDIT_PARAMETRES(new EditParametresCommand()),
    CONFIRM_EDITING_PARAMETRES(new ConfirmEditingParametresCommand()), ADD_TO_BASKET(new AddToBasketCommand()),
    BASKET(new BasketCommand()), MEDIA_LIBRARY(new MediaLibraryCommand()), BUY(new BuyCommand()),
    TOP_UP_ACCOUNT(new TopUpAccountCommand()), CONFIRM_TOPPING_UP(new ConfirmToppingUpCommand()),
    GIVE_FEEDBACK(new GiveFeedbackCommand()), CONFIRM_FEEDBACK(new ConfirmFeedbackCommand());

    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    CommandEnum(Command command){
        setCommand(command);
    }
}