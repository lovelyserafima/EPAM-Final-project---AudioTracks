package com.epam.audiomanager.util.property;

import com.epam.audiomanager.util.constant.ConstantBundles;
import com.epam.audiomanager.util.constant.ConstantLocales;
import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    EN(ResourceBundle.getBundle(ConstantBundles.MESSAGE, new Locale(ConstantLocales.ENGLISH_LOCALE))),
    RU(ResourceBundle.getBundle(ConstantBundles.MESSAGE, new Locale(ConstantLocales.RUSSIAN_LOCALE)));

    private ResourceBundle resourceBundle;

    MessageManager(ResourceBundle resourceBundle){
        this.resourceBundle = resourceBundle;
    }

    public String getMessage(String key){
        return resourceBundle.getString(key);
    }

    public static MessageManager defineLocale(String locale){
        MessageManager messageManager = null;
        if (locale == null){
            messageManager = messageManager.RU;
        }
        messageManager = (ConstantLocales.ENGLISH_LOCALE.equals(locale)) ? messageManager.EN : messageManager.RU;
        return messageManager;
    }

}
