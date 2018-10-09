package com.epam.audiomanager.command.impl.profile;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.entity.user.Client;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.logic.ChangeParametresLogic;
import com.epam.audiomanager.util.constant.ConstantAttributes;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class TopUpAccountCommand implements Command{
    private static final int CHANGES = 500;

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        HttpSession httpSession = httpServletRequest.getSession();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_PROFILE));
        //router.setRouteTypeRedirect();

        Client client = (Client) httpSession.getAttribute(ConstantAttributes.USER);
        BigDecimal clientMoney = client.getMoney();
        ChangeParametresLogic.topUpAccount(client.getId(), clientMoney, BigDecimal.valueOf(-CHANGES));
        client.setMoney(BigDecimal.valueOf(clientMoney.doubleValue() + CHANGES));
        return router;
    }
}
