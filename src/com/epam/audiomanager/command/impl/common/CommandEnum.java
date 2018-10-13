package com.epam.audiomanager.command.impl.common;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.command.impl.admin.audio.ReadMoreCommand;
import com.epam.audiomanager.command.impl.common.auth.SignInCommand;
import com.epam.audiomanager.command.impl.client.basket.AddToBasketCommand;
import com.epam.audiomanager.command.impl.client.basket.BasketCommand;
import com.epam.audiomanager.command.impl.client.basket.BuyCommand;
import com.epam.audiomanager.command.impl.client.profile.*;
import com.epam.audiomanager.command.impl.common.language.EnglishLanguageCommand;
import com.epam.audiomanager.command.impl.common.language.RussianLanguageCommand;
import com.epam.audiomanager.command.impl.common.main.MainCommand;
import com.epam.audiomanager.command.impl.client.medialibrary.ConfirmFeedbackCommand;
import com.epam.audiomanager.command.impl.client.medialibrary.GiveFeedbackCommand;
import com.epam.audiomanager.command.impl.client.medialibrary.MediaLibraryCommand;
import com.epam.audiomanager.command.impl.common.auth.registration.ConfirmRegistrationCommand;
import com.epam.audiomanager.command.impl.common.auth.registration.RegisterCommand;
import com.epam.audiomanager.command.impl.common.auth.registration.SignUpCommand;
import com.epam.audiomanager.command.impl.client.search.ShowAudioCommand;
import com.epam.audiomanager.command.impl.client.search.SearchMusicCommand;

public enum CommandEnum {
    SIGN_IN(new SignInCommand()), SIGN_UP(new SignUpCommand()), ENGLISH(new EnglishLanguageCommand()),
    RUSSIAN(new RussianLanguageCommand()), REGISTER(new RegisterCommand()), CONFIRM(new ConfirmRegistrationCommand()),
    SEARCH(new ShowAudioCommand()), MAIN(new MainCommand()), LOG_OUT(new LogOutCommand()), PROFILE(new ProfileCommand()),
    EDIT_PASSWORD(new EditPasswordCommand()), CONFIRM_EDITING_PASSWORD(new ConfirmEditingPasswordCommand()),
    SEARCH_MUSIC(new SearchMusicCommand()), EDIT_PARAMETRES(new EditParametresCommand()),
    CONFIRM_EDITING_PARAMETRES(new ConfirmEditingParametresCommand()), ADD_TO_BASKET(new AddToBasketCommand()),
    BASKET(new BasketCommand()), MEDIA_LIBRARY(new MediaLibraryCommand()), BUY(new BuyCommand()),
    TOP_UP_ACCOUNT(new TopUpAccountCommand()), CONFIRM_TOPPING_UP(new ConfirmToppingUpCommand()),
    GIVE_FEEDBACK(new GiveFeedbackCommand()), CONFIRM_FEEDBACK(new ConfirmFeedbackCommand()),
    READ_MORE(new ReadMoreCommand());

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