package com.epam.audiomanager.util.property;

import com.epam.audiomanager.util.constant.ConstantBundles;
import com.epam.audiomanager.util.constant.ConstantLocales;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
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
            messageManager = new MessageManager(ResourceBundle.getBundle(ConstantBundles.MESSAGE,
                    new Locale(ConstantLocales.RUSSIAN_LOCALE)));
        } else {
            switch (locale){
                case ConstantLocales.ENGLISH_LOCALE:
                    messageManager = new MessageManager(ResourceBundle.getBundle(ConstantBundles.MESSAGE,
                            new Locale(ConstantLocales.ENGLISH_LANGUAGE, ConstantLocales.US)));
                    break;
                case ConstantLocales.RUSSIAN_LOCALE:
                    messageManager = new MessageManager(ResourceBundle.getBundle(ConstantBundles.MESSAGE,
                            new Locale(ConstantLocales.RUSSIAN_LANGUAGE, ConstantLocales.RUSSIA)));
                    break;
            }
        }
        return messageManager;
    }

}
