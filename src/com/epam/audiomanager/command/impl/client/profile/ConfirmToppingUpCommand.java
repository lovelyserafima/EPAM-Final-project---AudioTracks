package com.epam.audiomanager.command.impl.client.profile;

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

public class ConfirmToppingUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        HttpSession httpSession = httpServletRequest.getSession();
        BigDecimal addedMoney = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(
                ConstantAttributes.ADDED_MONEY)));

        Client client = (Client) httpSession.getAttribute(ConstantAttributes.USER);
        BigDecimal clientMoney = client.getMoney();
        ChangeParametresLogic.topUpAccount(client.getId(), clientMoney, BigDecimal.valueOf(addedMoney.doubleValue()*-1));
        client.setMoney(BigDecimal.valueOf(clientMoney.doubleValue() + addedMoney.doubleValue()));

        router.setRouteTypeRedirect();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_PROFILE));
        return router;
    }
}
