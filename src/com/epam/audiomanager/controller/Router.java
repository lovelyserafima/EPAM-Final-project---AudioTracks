package com.epam.audiomanager.controller;

import com.epam.audiomanager.util.constant.ConstantPathPages;
import com.epam.audiomanager.util.property.ConfigurationManager;

public class Router {
    private String pagePath = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
    private RouteType routeType = RouteType.FORWARD;

    public String getPagePath(){
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        if (pagePath != null) {
            this.pagePath = pagePath;
        }
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteTypeRedirect() {
        this.routeType = RouteType.REDIRECT;
    }
}