package com.epam.audiomanager.command.impl.client.profile;

import com.epam.audiomanager.command.Command;
import com.epam.audiomanager.controller.Router;
import com.epam.audiomanager.exception.ProjectException;
import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class TopUpAccountCommand implements Command{
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws ProjectException {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_TOP_UP_ACCOUNT));
        return router;
    }
}

    /*Router router = new Router();
    HttpSession httpSession = httpServletRequest.getSession();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_CLIENT_PROFILE));

                Client client = (Client) httpSession.getAttribute(ConstantAttributes.USER);
                BigDecimal clientMoney = client.getMoney();
                ChangeParametresLogic.topUpAccount(client.getId(), clientMoney, BigDecimal.valueOf(-CHANGES));
                client.setMoney(BigDecimal.valueOf(clientMoney.doubleValue() + CHANGES));
                return router;*/
